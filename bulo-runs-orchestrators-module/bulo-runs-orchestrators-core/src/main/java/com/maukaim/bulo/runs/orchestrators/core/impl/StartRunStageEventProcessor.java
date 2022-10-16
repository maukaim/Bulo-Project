package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.core.factories.StageRunFactory;

import java.time.Instant;
import java.util.Map;

public class StartRunStageEventProcessor extends StageEventProcessor {
    private final StageRunService stageRunService;

    public StartRunStageEventProcessor(FlowRunService flowRunService,
                                       StageRunService stageRunService) {
        super(flowRunService);
        this.stageRunService = stageRunService;
    }

    public void process(String stageRunId, Instant instant, String flowRunId) {
        System.out.println("Run Started event... " + stageRunId);
        this.flowRunService.computeStageRunViewUnderLock(flowRunId, (actualFlowRun) -> {
            StageRun actualView = actualFlowRun.getStageRunsById().get(stageRunId);
            if (actualView == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }
            if(actualFlowRun.getFlowRunStatus().isProblem() && !actualView.getStageRunStatus().isTerminal()){
                if(actualView.getExecutorId() == null){
                    this.stageRunService.requestCancel(stageRunId);
                }else{
                    this.stageRunService.requestCancel(stageRunId, actualView.getExecutorId());
                }
            }
            StageRun nextView = StageRunFactory.launched(actualView, instant);
            return Map.of(stageRunId, nextView);
        });
    }
}
// IDEA:
//Bouger les serialization et io qui sont pas pour les Instructions humaines dans les ms
// Triggers, Stages, Flows
//Et puis save tout ca un jour....