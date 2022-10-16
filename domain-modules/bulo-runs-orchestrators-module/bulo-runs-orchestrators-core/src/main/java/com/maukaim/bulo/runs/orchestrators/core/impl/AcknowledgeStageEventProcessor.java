package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.core.factories.StageRunFactory;

import java.util.Map;

public class AcknowledgeStageEventProcessor extends StageEventProcessor {
    private final StageRunService stageRunService;
    public AcknowledgeStageEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService);
        this.stageRunService = stageRunService;
    }

    public void process(String stageRunId, String executorId , String flowRunId) {
        System.out.println("Acknowledged Run event... " + stageRunId);
        this.flowRunService.computeStageRunViewUnderLock(flowRunId, (actualFlowRun) -> {
            StageRun stageRun = actualFlowRun.getStageRunsById().get(stageRunId);
            if(actualFlowRun.getFlowRunStatus().isProblem() && !stageRun.getStageRunStatus().isTerminal()){
                    this.stageRunService.requestCancel(stageRun.getStageRunId(), executorId);
            }
            return Map.of(stageRunId, StageRunFactory.acknowledged(stageRun, executorId));
        });
    }
}
