package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.exceptions.FlowRunStartException;
import com.maukaim.bulo.runs.orchestrators.core.FlowService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.utils.FlowUtils;
import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.core.factories.FlowRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.CloseableEntityLock;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public FlowRun startRun(String flowId, Set<FlowStageId> rootStageIds) {
        Flow flow = this.getExistingFlow(flowId);
        Set<FlowStageId> rootIds = FlowUtils.getRootIds(flow);
        Set<FlowStageId> flowStagesToRunId;
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
        Map<String, StageRun> stageRunById = this.stageRunService.startRuns(newRunPersisted.getFlowRunId(), resolve(flowStagesToRunId));

        return this.computeStageRunViewUnderLock(newRunPersisted.getFlowRunId(), (previous) -> stageRunById);
    }

    private Map<FlowStageId, Set<StageRunDependency>> resolve(Set<FlowStageId> flowStageIds) {
        return flowStageIds == null ? Map.of() : flowStageIds.stream()
                .collect(Collectors.toMap(flowStageId -> flowStageId, flowStageId -> Set.of()));
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

    @Override
    public FlowRun computeStageRunViewUnderLock(String flowRunId, Function<FlowRun, Map<String, StageRun>> stageRunViewComputer) {
        try (CloseableEntityLock<FlowRun> lockedEntity = this.flowRunStore.getAndLock(flowRunId)) {
            FlowRun flowRun = lockedEntity.getEntity();
            Map<String, StageRun> stageRunViewToUpdate = stageRunViewComputer.apply(flowRun);
            FlowRun newFlowRunValue = FlowRunFactory.updateStageRunView(flowRun, stageRunViewToUpdate);
            newFlowRunValue = FlowRunFactory.updateState(newFlowRunValue, resolveStatus(newFlowRunValue));
            return this.flowRunStore.put(newFlowRunValue);
        }
    }

    private FlowRunStatus resolveStatus(FlowRun flowRun) {
        FlowRunStatus actualStatus = flowRun.getFlowRunStatus();
        if (actualStatus.isTerminal()) {
            return actualStatus;
        } else {
            Map<String, StageRun> stageRunViewByStageId = flowRun.getStageRunsById();
            boolean anyCancelled = false;
            boolean anyFailed = false;
            boolean anyRunning = false;
            boolean anyAcknowledged = false;
            boolean anySuccessful = false;
            for (Map.Entry<String, StageRun> stageRunViewById : stageRunViewByStageId.entrySet()) {
                switch (stageRunViewById.getValue().getStageRunStatus()) {
                    case RUNNING -> anyRunning = true;
                    case CANCELLED -> anyCancelled = true;
                    case FAILED -> anyFailed = true;
                    case ACKNOWLEDGED -> anyAcknowledged = true;
                    case SUCCESS -> anySuccessful = true;
                }
            }

            if (anyFailed) return FlowRunStatus.FAILED;
            else if (anyCancelled) return FlowRunStatus.CANCELLED;
            else if (anyRunning) return FlowRunStatus.RUNNING;
            else if (flowRun.allRunsAreTerminated()) return FlowRunStatus.SUCCESS;
            else if (FlowRunStatus.NEW.equals(actualStatus) && (anyAcknowledged)) return FlowRunStatus.PENDING_START;
            else if (anySuccessful) return FlowRunStatus.RUNNING;
            else return actualStatus;
        }
    }
}
