package com.maukaim.boule.flows.orchestrator.stage.processors;

import com.maukaim.boule.flows.orchestrator.publisher.StageRunEventPublisher;
import com.maukaim.boule.flows.orchestrator.run.FlowRunService;
import com.maukaim.boule.flows.orchestrator.stage.StageEventProcessor;
import com.maukaim.boule.flows.orchestrator.stage.event.RunCancelledStageEvent;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunView;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunViewFactory;

import java.util.Map;

public class RunCancelledStageEventProcessor extends StageEventProcessor<RunCancelledStageEvent> {
    private final StageRunEventPublisher stageRunEventPublisher;
    public RunCancelledStageEventProcessor(FlowRunService flowRunService,
                                           StageRunEventPublisher stageRunEventPublisher) {
        super(flowRunService);
        this.stageRunEventPublisher = stageRunEventPublisher;
    }

    @Override
    public Class<RunCancelledStageEvent> getExpectedStageEventClass() {
        return RunCancelledStageEvent.class;
    }

    @Override
    public void process(RunCancelledStageEvent event) {
        System.out.println("Received CANCELLED run Event, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(event.getFlowRunId(), (actualFlowRun) -> {
            StageRunView actualView = actualFlowRun.getViewByStageId().get(event.getStageId());
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            StageRunView computedView = StageRunViewFactory.cancelled(actualView);
            return Map.of(event.getStageId(), computedView);
        });
    }
}
