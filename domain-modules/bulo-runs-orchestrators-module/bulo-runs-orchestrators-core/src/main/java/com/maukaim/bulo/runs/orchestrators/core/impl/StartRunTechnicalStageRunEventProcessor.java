package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.TechnicalStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FlowContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;

import java.time.Instant;
import java.util.Map;

public class StartRunTechnicalStageRunEventProcessor extends TechnicalStageRunEventProcessor {

    public StartRunTechnicalStageRunEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService, stageRunService);
    }

    public void process(String stageRunId, Instant instant, FunctionalStageContext context) {
        System.out.println("Run Started event... " + stageRunId);
        stageRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFunctionalStageRun)-> commonProcess(actualFunctionalStageRun, stageRunId, instant));
    }

    public void process(String stageRunId, Instant instant, FlowContext context) {
        System.out.println("Run Started event... " + stageRunId);
        flowRunService.computeStageRunUpdateUnderLock(context.getContextId(), (actualFlowRun) -> commonProcess(actualFlowRun, stageRunId, instant));
    }

    private Map<String, StageRun> commonProcess(OrchestrableContext<?> orchestrableContext, String stageRunId, Instant instant){
        TechnicalStageRun actualRun = getActualTechnicalRun(orchestrableContext, stageRunId);
        if (actualRun == null) {
            throw new IllegalArgumentException("This stage id was not requested to run under this Context.");
        }
        if(orchestrableContext.getStatus().isProblem() && !actualRun.getStatus().isTerminal()){
            if(actualRun.getExecutorId() == null){
                this.stageRunService.requestCancel(stageRunId);
            }else{
                this.stageRunService.requestCancel(stageRunId, actualRun.getExecutorId());
            }
        }
        StageRun nextView = TechnicalStageRunFactory.launched(actualRun, instant);
        return Map.of(stageRunId, nextView);
    }
}