package com.maukaim.boule.flows.orchestrator.stage.processors;

import com.maukaim.boule.flows.orchestrator.publisher.StageRunEventPublisher;
import com.maukaim.boule.flows.orchestrator.run.FlowRunService;
import com.maukaim.boule.flows.orchestrator.stage.StageEventProcessor;
import com.maukaim.boule.flows.orchestrator.stage.event.RunFailedStageEvent;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunView;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunViewFactory;

import java.util.Map;

public class RunFailedStageEventProcessor extends StageEventProcessor<RunFailedStageEvent> {
    private final StageRunEventPublisher stageRunEventPublisher;

    public RunFailedStageEventProcessor(FlowRunService flowRunService, StageRunEventPublisher stageRunEventPublisher) {
        super(flowRunService);
        this.stageRunEventPublisher = stageRunEventPublisher;
    }

    @Override
    public Class<RunFailedStageEvent> getExpectedStageEventClass() {
        return RunFailedStageEvent.class;
    }

    @Override
    public void process(RunFailedStageEvent event) {
        System.out.println("Received FAILED run Event, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(event.getFlowRunId(), (actualFlowRun) -> {
            StageRunView actualView = actualFlowRun.getViewByStageId().get(event.getStageId());
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            for (String runningStage : actualFlowRun.getRunningStages()) {
                if(!event.getStageId().equals(runningStage)) {
                    this.stageRunEventPublisher.requestSyncCancel(actualFlowRun.getFlowRunId(), runningStage);
                }
            }

            return Map.of(event.getStageId(), StageRunViewFactory.failed(actualView));
        });
    }
}
