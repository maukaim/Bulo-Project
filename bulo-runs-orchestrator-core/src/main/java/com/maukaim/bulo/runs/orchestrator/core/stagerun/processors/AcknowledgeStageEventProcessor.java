package com.maukaim.bulo.runs.orchestrator.core.stagerun.processors;

import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunService;
import com.maukaim.bulo.runs.orchestrator.io.in.AcknowledgeRequestStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRun;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRunFactory;

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
            if (stageRun == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }
            if(actualFlowRun.getFlowRunStatus().isProblem() && !stageRun.getStageRunStatus().isTerminal()){
                    this.stageRunService.requestCancel(stageRun.getStageRunId(), event.getExecutorId());
            }
            return Map.of(event.getStageRunId(), StageRunFactory.acknowledged(stageRun, event.getExecutorId()));
        });
    }
}
