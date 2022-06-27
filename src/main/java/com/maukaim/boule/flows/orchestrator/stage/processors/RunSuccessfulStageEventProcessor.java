package com.maukaim.boule.flows.orchestrator.stage.processors;

import com.maukaim.boule.flows.orchestrator.publisher.StageRunEventPublisher;
import com.maukaim.boule.flows.orchestrator.run.FlowRun;
import com.maukaim.boule.flows.orchestrator.run.FlowRunService;
import com.maukaim.boule.flows.orchestrator.stage.StageEventProcessor;
import com.maukaim.boule.flows.orchestrator.stage.event.RunSuccessfulStageEvent;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunView;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunViewFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class RunSuccessfulStageEventProcessor extends StageEventProcessor<RunSuccessfulStageEvent> {
    private final StageRunEventPublisher stageRunEventPublisher;

    public RunSuccessfulStageEventProcessor(FlowRunService flowRunService, StageRunEventPublisher stageRunEventPublisher) {
        super(flowRunService);
        this.stageRunEventPublisher = stageRunEventPublisher;
    }

    @Override
    public Class<RunSuccessfulStageEvent> getExpectedStageEventClass() {
        return RunSuccessfulStageEvent.class;
    }

    @Override
    public void process(RunSuccessfulStageEvent event) {
        System.out.println("Received Successful run Event, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(event.getFlowRunId(), (actualFlowRun) -> {
            StageRunView actualView = actualFlowRun.getViewByStageId().get(event.getStageId());
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            Map<String, StageRunView> result = new HashMap<>();
            result.put(event.getStageId(), StageRunViewFactory.success(actualView));

            Set<String> children = actualFlowRun.getExecutionGraph().getChildren(event.getStageId());
            if(!actualFlowRun.getFlowRunStatus().isTerminal() && !children.isEmpty()){
                    result.putAll(this.requestRunIfAuthorized(actualFlowRun, event.getStageId(), children));
            }

            return result;
        });
    }

    private Map<String, StageRunView> requestRunIfAuthorized(FlowRun flowRun, String excludedAncestor, Set<String> childrenToRun) {
        String flowRunId = flowRun.getFlowRunId();
        return childrenToRun.stream()
                .filter(childStageId -> flowRun.otherAncestorsAreSuccessful(childStageId, excludedAncestor))
                .peek(childStageId -> this.stageRunEventPublisher.requestAsyncRun(flowRunId, Set.of(childStageId)))
                .collect(toMap(stageId -> stageId, stageId -> StageRunViewFactory.requested(flowRunId, stageId)));
    }
}
