package com.maukaim.boule.flows.orchestrator.stage.processors;

import com.maukaim.boule.flows.orchestrator.run.FlowRunService;
import com.maukaim.boule.flows.orchestrator.stage.event.AcknowledgeRequestStageEvent;
import com.maukaim.boule.flows.orchestrator.stage.StageEventProcessor;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunView;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunViewFactory;

import java.util.Map;

public class AcknowledgeStageEventProcessor extends StageEventProcessor<AcknowledgeRequestStageEvent> {
    public AcknowledgeStageEventProcessor(FlowRunService flowRunService) {
        super(flowRunService);
    }

    @Override
    public Class<AcknowledgeRequestStageEvent> getExpectedStageEventClass() {
        return AcknowledgeRequestStageEvent.class;
    }

    @Override
    public void process(AcknowledgeRequestStageEvent event) {
        System.out.println("Received Acknowledgment Event, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(event.getFlowRunId(), (actualFlowRun) -> {
            StageRunView stageRunView = actualFlowRun.getViewByStageId().get(event.getStageId());
            if (stageRunView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }
            return Map.of(event.getStageId(), StageRunViewFactory.acknowledged(stageRunView, event.getExecutorId()));
        });
    }
}
