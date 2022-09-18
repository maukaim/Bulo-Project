package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.io.events.AcknowledgeRequestStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.core.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.core.factories.StageRunFactory;

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
            StageRun stageRun = actualFlowRun.getStageRunsById().get(event.getStageRunId());
            if(actualFlowRun.getFlowRunStatus().isProblem() && !stageRun.getStageRunStatus().isTerminal()){
                    this.stageRunService.requestCancel(stageRun.getStageRunId(), event.getExecutorId());
            }
            return Map.of(event.getStageRunId(), StageRunFactory.acknowledged(stageRun, event.getExecutorId()));
        });
    }
}
