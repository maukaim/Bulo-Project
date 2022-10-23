package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.TechnicalStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FlowContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;

import java.util.Map;

public class AcknowledgeTechnicalStageRunEventProcessor extends TechnicalStageRunEventProcessor {
    public AcknowledgeTechnicalStageRunEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService, stageRunService);
    }

    public void process(String stageRunId, String executorId, FunctionalStageContext context) {
        System.out.println("Acknowledged Run event... " + stageRunId);
        stageRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFunctionalStageRun)-> commonProcess(actualFunctionalStageRun, stageRunId, executorId));
    }

    public void process(String stageRunId, String executorId, FlowContext context) {
        System.out.println("Acknowledged Run event... " + stageRunId);
        flowRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFunctionalStageRun)-> commonProcess(actualFunctionalStageRun, stageRunId, executorId));
    }

    private Map<String, StageRun> commonProcess(OrchestrableContext<?> orchestrableContext, String stageRunId, String executorId){
        TechnicalStageRun stageRun = getActualTechnicalRun(orchestrableContext ,stageRunId);
        if(orchestrableContext.getStatus().isProblem() && !stageRun.getStatus().isTerminal()){
            this.stageRunService.requestCancel(stageRun.getStageRunId(), executorId);
        }
        return Map.of(stageRunId, TechnicalStageRunFactory.acknowledged(stageRun, executorId));
    }

}
