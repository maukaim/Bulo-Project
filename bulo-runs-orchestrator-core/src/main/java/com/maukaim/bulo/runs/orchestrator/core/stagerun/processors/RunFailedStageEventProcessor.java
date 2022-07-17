package com.maukaim.bulo.runs.orchestrator.core.stagerun.processors;

import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunService;
import com.maukaim.bulo.runs.orchestrator.io.in.RunFailedStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRun;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRunFactory;

import java.util.Map;

public class RunFailedStageEventProcessor extends StageEventProcessor<RunFailedStageRunEvent> {
    private final StageRunService stageRunService;

    public RunFailedStageEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService);
        this.stageRunService = stageRunService;
    }

    @Override
    public Class<RunFailedStageRunEvent> getExpectedStageEventClass() {
        return RunFailedStageRunEvent.class;
    }

    @Override
    public void process(RunFailedStageRunEvent event, String flowRunId) {
        System.out.println("Received FAILED run Event, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(flowRunId, (actualFlowRun) -> {
            StageRun actualView = actualFlowRun.getStageRunsById().get(event.getStageRunId());
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            for (StageRun stageRun : actualFlowRun.getInFlightStageRuns()) {
                String stageRunId = stageRun.getStageRunId();
                if(!event.getStageRunId().equals(stageRunId)) {
                    if(stageRun.getExecutorId() == null){
                        this.stageRunService.requestCancel(stageRunId);
                    }else{
                        this.stageRunService.requestCancel(stageRunId, stageRun.getExecutorId());
                    }
                }
            }

            return Map.of(event.getStageRunId(), StageRunFactory.failed(actualView, event.getInstant()));
        });
    }
}
