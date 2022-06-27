package com.maukaim.boule.flows.orchestrator.run;

import com.maukaim.boule.flows.orchestrator.flow.FlowService;
import com.maukaim.boule.flows.orchestrator.flow.exceptions.FlowStartException;
import com.maukaim.boule.flows.orchestrator.flow.model.Flow;
import com.maukaim.boule.flows.orchestrator.publisher.StageRunEventPublisher;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunView;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunViewFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.maukaim.boule.flows.orchestrator.run.FlowRunStatus.NEW;
import static com.maukaim.boule.flows.orchestrator.run.FlowRunStatus.PENDING_START;

@Service
public class FlowRunServiceImpl implements FlowRunService {

    private final FlowService flowService;
    private final FlowRunCache flowRunCache;
    private final StageRunEventPublisher stageRunEventPublisher;

    @Autowired
    public FlowRunServiceImpl(FlowService flowService, FlowRunCache flowRunCache,
                              StageRunEventPublisher stageRunEventPublisher) {
        this.flowService = flowService;
        this.flowRunCache = flowRunCache;
        this.stageRunEventPublisher = stageRunEventPublisher;
    }

    @Override
    public FlowRun startRun(String flowId, String rootStageId) {
        Flow flow = this.getExistingFlow(flowId);

        Set<String> stagesToRun;
        if (rootStageId == null) {
            stagesToRun = flow.getExecutionGraph().getRootsIds();
        } else if (flow.isRootStage(rootStageId)) {
            stagesToRun = Set.of(rootStageId);
        } else {
            throw new FlowStartException(String.format("Flow [%s] does not recognize Stage [%s] as a root",
                    flow.getFlowId(),
                    rootStageId));
        }

        FlowRun newRunNonPersisted = FlowRunFactory.newRun(flow);
        FlowRun newRunPersisted = this.flowRunCache.add(newRunNonPersisted);
        boolean startRequested = this.stageRunEventPublisher.requestAsyncRun(newRunPersisted.getFlowRunId(), stagesToRun);

        Map<String, StageRunView> stageRunViewMap;
        if (startRequested) {
            stageRunViewMap = stagesToRun.stream().collect(Collectors.toMap(stageId -> stageId,
                    stageId -> StageRunViewFactory.requested(newRunPersisted.getFlowRunId(), stageId)));
        } else {
            stageRunViewMap = stagesToRun.stream().collect(Collectors.toMap(stageId -> stageId,
                    stageId -> StageRunViewFactory.failed(StageRunViewFactory.requested(newRunPersisted.getFlowRunId(), stageId))));
        }

        return this.computeStageRunViewUnderLock(newRunPersisted.getFlowRunId(), (previous) -> {
            return stageRunViewMap;
        });
    }

    private Flow getExistingFlow(String flowId) {
        Optional<Flow> optionalFlow = this.flowService.getFlow(flowId);
        if (optionalFlow.isEmpty()) {
            throw new FlowStartException("No flow existing with id: " + flowId);
        }
        return optionalFlow.get();
    }

    @Override
    public FlowRun getById(String flowRunId) {
        return flowRunCache.getRun(flowRunId);
    }

    @Override
    public FlowRun computeStageRunViewUnderLock(String flowRunId, Function<FlowRun, Map<String, StageRunView>> stageRunViewProvider) {
        try (var lockedEntity = this.flowRunCache.getAndLock(flowRunId)) {
            FlowRun flowRun = lockedEntity.getValue();
            Map<String, StageRunView> stageRunViewToUpdate = stageRunViewProvider.apply(flowRun);
            FlowRun newFlowRunValue = FlowRunFactory.updateStageRunView(flowRun, stageRunViewToUpdate);
            newFlowRunValue = FlowRunFactory.updateState(newFlowRunValue, resolveStatus(newFlowRunValue));
            return this.flowRunCache.update(newFlowRunValue);
        }
    }

    private FlowRunStatus resolveStatus(FlowRun flowRun){
        FlowRunStatus actualStatus = flowRun.getFlowRunStatus();
        if(actualStatus.isTerminal()){
            return actualStatus;
        }

        Map<String, StageRunView> stageRunViewByStageId = flowRun.getViewByStageId();
        boolean anyCancelled = false;
        boolean anyFailed = false;
        boolean anyRunning = false;
        boolean anyAcknowledged = false;
        for (Map.Entry<String, StageRunView> stageRunViewById : stageRunViewByStageId.entrySet()) {
            switch (stageRunViewById.getValue().getStageRunStatus()){
                case RUNNING -> anyRunning = true;
                case CANCELLED -> anyCancelled = true;
                case FAILED -> anyFailed = true;
                case ACKNOWLEDGED -> anyAcknowledged = true;
            }
        }

        if(anyCancelled) return FlowRunStatus.CANCELLED;
        if(anyFailed) return FlowRunStatus.FAILED;
        if(anyRunning) return FlowRunStatus.RUNNING;

        if(flowRun.allRunsAreTerminated()){
            return FlowRunStatus.SUCCESS;
        }

        if(NEW.equals(actualStatus) && anyAcknowledged){
            return PENDING_START;
        }

        return actualStatus;
    }
}
