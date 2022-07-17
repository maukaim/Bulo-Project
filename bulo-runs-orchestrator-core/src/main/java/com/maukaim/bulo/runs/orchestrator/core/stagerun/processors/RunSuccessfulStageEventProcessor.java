package com.maukaim.bulo.runs.orchestrator.core.stagerun.processors;

import com.maukaim.bulo.flows.api.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.model.FlowRun;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunService;
import com.maukaim.bulo.runs.orchestrator.io.in.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRun;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRunFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class RunSuccessfulStageEventProcessor extends StageEventProcessor<RunSuccessfulStageRunEvent> {
    private final StageRunService stageRunService;

    public RunSuccessfulStageEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService);
        this.stageRunService = stageRunService;
    }

    @Override
    public Class<RunSuccessfulStageRunEvent> getExpectedStageEventClass() {
        return RunSuccessfulStageRunEvent.class;
    }

    @Override
    public void process(RunSuccessfulStageRunEvent event, String flowRunId) {
        System.out.println("Received Successful run Event, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(flowRunId, (actualFlowRun) -> {
            StageRun actualRunView = actualFlowRun.getStageRunsById().get(event.getStageRunId());
            if (actualRunView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            Map<String, StageRun> result = new HashMap<>();
            result.put(event.getStageRunId(), StageRunFactory.success(actualRunView, event.getInstant()));

            Set<FlowStageId> childrenIds = actualFlowRun.getExecutionGraph().getChildren(actualRunView.getFlowStageId());
            if(!actualFlowRun.getFlowRunStatus().isTerminal() && !childrenIds.isEmpty()){
                    result.putAll(this.requestRunIfAuthorized(actualFlowRun, actualRunView.getFlowStageId(), childrenIds));
            }

            return result;
        });
    }

    private Map<String, StageRun> requestRunIfAuthorized(FlowRun flowRun, FlowStageId currentStageId, Set<FlowStageId> children) {
        String flowRunId = flowRun.getFlowRunId();
        Set<FlowStageId> childrenToStart = children.stream()
                .filter(childStageId -> flowRun.otherAncestorsAreSuccessful(childStageId, currentStageId))
                .collect(toUnmodifiableSet());
        return this.stageRunService.startRuns(flowRunId, childrenToStart);
    }
}
