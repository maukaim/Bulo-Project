package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.FlowService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.exceptions.FlowRunStartException;
import com.maukaim.bulo.runs.orchestrators.core.factories.FlowRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.utils.FlowUtils;
import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.maukaim.bulo.runs.orchestrators.core.utils.OrchestrableContextStatusResolver.resolveStatus;

public class FlowRunServiceImpl implements FlowRunService {

    private final FlowService flowService;
    private final FlowRunStore flowRunStore;
    private final StageRunService stageRunService;

    public FlowRunServiceImpl(FlowService flowService,
                              FlowRunStore flowRunStore,
                              StageRunService stageRunService) {
        this.flowService = flowService;
        this.flowRunStore = flowRunStore;
        this.stageRunService = stageRunService;
    }

    @Override
    public FlowRun startRun(String flowId, Set<ContextualizedStageId> rootStageIds) {
        Flow flow = this.getExistingFlow(flowId);
        Set<ContextualizedStageId> rootIds = FlowUtils.getRootIds(flow);
        Set<ContextualizedStageId> flowStagesToRunId;
        if (rootStageIds == null || rootStageIds.isEmpty()) {
            flowStagesToRunId = rootIds;
        } else if (rootIds.containsAll(rootStageIds)) {
            flowStagesToRunId = rootStageIds;
        } else {
            throw new FlowRunStartException(String.format("Flow [%s] does not recognize one of the FlowStages %s as a root.",
                    flow.getFlowId(),
                    rootStageIds));
        }

        FlowRun newRunNonPersisted = FlowRunFactory.create(flow);
        FlowRun newRunPersisted = this.flowRunStore.add(newRunNonPersisted);
        Map<String, StageRun> stageRunsToRequest = this.stageRunService.getNextStageRuns(newRunPersisted.toRunContext(), resolveLocalRunDependenciesForRoots(flowStagesToRunId));
        return this.computeStageRunUpdateUnderLock(newRunPersisted.getContextId(), (previous) -> stageRunsToRequest);
    }

    private Map<ContextualizedStageId, Set<RunDependency>> resolveLocalRunDependenciesForRoots(Set<ContextualizedStageId> contextualizedStageIds) {
        return contextualizedStageIds == null ? Map.of() : contextualizedStageIds.stream()
                .collect(Collectors.toMap(flowStageId -> flowStageId, flowStageId -> Set.of())); // No inputs for roots in FlowRUn
    }

    private Flow getExistingFlow(String flowId) {
        Optional<Flow> optionalFlow = this.flowService.getFlow(flowId);
        if (optionalFlow.isEmpty()) {
            throw new FlowRunStartException("No flow existing with id: " + flowId);
        }
        return optionalFlow.get();
    }

    @Override
    public FlowRun getById(String flowRunId) {
        return flowRunStore.getRun(flowRunId);
    }

    //TODO -> How to make a better lock? maybe here : https://stackoverflow.com/questions/51716527/how-to-lock-on-key-in-a-concurrenthashmap

    @Override
    public synchronized FlowRun computeStageRunUpdateUnderLock(String flowRunId, Function<FlowRun, Map<String, StageRun>> stageRunViewComputer) {
        AtomicReference<List<StageRun>> toBeRequestedReference = new AtomicReference<>();
        FlowRun flowRunPersisted = this.flowRunStore.compute(flowRunId, (id, flowRun) -> {
            Map<String, StageRun> stageRunViewToUpdate = stageRunViewComputer.apply(flowRun);
            FlowRun newFlowRunValue = FlowRunFactory.updateStageRunView(flowRun, stageRunViewToUpdate);
            newFlowRunValue = FlowRunFactory.updateState(newFlowRunValue, resolveStatus(newFlowRunValue));

            List<StageRun> tobeRequestedTechnicalStageRuns = stageRunViewToUpdate.values().stream()
                    .filter(stageRun -> stageRun.getStatus().isRunNeeded())
                    .collect(Collectors.toList());
            toBeRequestedReference.set(tobeRequestedTechnicalStageRuns);
            return newFlowRunValue;
        });

        List<StageRun> toBeRequestedRuns = toBeRequestedReference.get();
        if(toBeRequestedRuns != null && !toBeRequestedRuns.isEmpty()){
            return this.flowRunStore.compute(flowRunId,(id,flowRun)->{
                Map<String, StageRun> stageRunsAfterRequest = this.stageRunService.startRuns(toBeRequestedRuns);
                FlowRun flowRunAfterRequested = FlowRunFactory.updateStageRunView(flowRunPersisted, stageRunsAfterRequest);
                flowRunAfterRequested = FlowRunFactory.updateState(flowRunAfterRequested, resolveStatus(flowRunAfterRequested));

                return flowRunAfterRequested;
            });
        }
        return flowRunPersisted;
    }
}
