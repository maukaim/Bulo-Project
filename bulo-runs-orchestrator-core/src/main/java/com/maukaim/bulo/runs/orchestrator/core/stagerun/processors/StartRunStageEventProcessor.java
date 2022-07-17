package com.maukaim.bulo.runs.orchestrator.core.stagerun.processors;

import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunService;
import com.maukaim.bulo.runs.orchestrator.io.in.StartRunStageRunEvent;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRun;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRunFactory;

import java.util.Map;

public class StartRunStageEventProcessor extends StageEventProcessor<StartRunStageRunEvent> {
    private final StageRunService stageRunService;

    public StartRunStageEventProcessor(FlowRunService flowRunService,
                                       StageRunService stageRunService) {
        super(flowRunService);
        this.stageRunService = stageRunService;
    }
    @Override
    public Class<StartRunStageRunEvent> getExpectedStageEventClass() {
        return StartRunStageRunEvent.class;
    }

    @Override
    public void process(StartRunStageRunEvent event, String flowRunId) {
        System.out.println("Received StartRunEvent, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(flowRunId, (actualFlowRun) -> {
            StageRun actualView = actualFlowRun.getStageRunsById().get(event.getStageRunId());
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }
            if(actualFlowRun.getFlowRunStatus().isProblem() && !actualView.getStageRunStatus().isTerminal()){
                if(actualView.getExecutorId() == null){
                    this.stageRunService.requestCancel(event.getStageRunId());
                }else{
                    this.stageRunService.requestCancel(event.getStageRunId(), actualView.getExecutorId());
                }
            }
            StageRun nextView = StageRunFactory.launched(actualView, event.getInstant());
            return Map.of(event.getStageRunId(), nextView);
        });
    }
}
