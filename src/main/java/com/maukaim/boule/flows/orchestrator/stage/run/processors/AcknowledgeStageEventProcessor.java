package com.maukaim.boule.flows.orchestrator.stage.run.processors;

import com.maukaim.boule.flows.orchestrator.flow.FlowRunService;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunService;
import com.maukaim.boule.flows.orchestrator.stage.run.event.AcknowledgeRequestStageRunEvent;
import com.maukaim.boule.flows.orchestrator.stage.run.StageEventProcessor;
import com.maukaim.boule.flows.orchestrator.stage.run.model.StageRunView;
import com.maukaim.boule.flows.orchestrator.stage.run.model.StageRunViewFactory;

import java.util.Map;

public class AcknowledgeStageEventProcessor extends StageEventProcessor<AcknowledgeRequestStageRunEvent> {
    private final StageRunService stageRunService;
    public AcknowledgeStageEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService);
        this.stageRunService = stageRunService;
    }

    @Override
    public Class<AcknowledgeRequestStageRunEvent> getExpectedStageEventClass() {
        return AcknowledgeRequestStageRunEvent.class;
    }

    @Override
    public void process(AcknowledgeRequestStageRunEvent event, String flowRunId) {
        System.out.println("Received Acknowledgment Event, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(flowRunId, (actualFlowRun) -> {
            StageRunView stageRunView = actualFlowRun.getStageRunsById().get(event.getStageRunId());
            if (stageRunView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }
            if(actualFlowRun.getFlowRunStatus().isProblem() && !stageRunView.getStageRunStatus().isTerminal()){
                    this.stageRunService.requestCancel(stageRunView.getStageRunId(), event.getExecutorId());
            }
            return Map.of(event.getStageRunId(), StageRunViewFactory.acknowledged(stageRunView, event.getExecutorId()));
        });
    }
}
