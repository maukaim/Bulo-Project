package com.maukaim.bulo.runs.orchestrator.stage.run.processors;

import com.maukaim.bulo.runs.orchestrator.flow.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.stage.run.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.stage.run.StageRunService;
import com.maukaim.bulo.runs.orchestrator.stage.run.event.RunFailedStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunView;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunViewFactory;

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
            StageRunView actualView = actualFlowRun.getStageRunsById().get(event.getStageRunId());
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            for (StageRunView stageRunView : actualFlowRun.getInFlightStageRuns()) {
                String stageRunId = stageRunView.getStageRunId();
                if(!event.getStageRunId().equals(stageRunId)) {
                    if(stageRunView.getExecutorId() == null){
                        this.stageRunService.requestCancel(stageRunId);
                    }else{
                        this.stageRunService.requestCancel(stageRunId, stageRunView.getExecutorId());
                    }
                }
            }

            return Map.of(event.getStageRunId(), StageRunViewFactory.failed(actualView, event.getInstant()));
        });
    }
}
