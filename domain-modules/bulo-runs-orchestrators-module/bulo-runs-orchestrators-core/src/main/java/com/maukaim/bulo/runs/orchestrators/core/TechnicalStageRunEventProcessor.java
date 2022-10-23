package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;

public abstract class TechnicalStageRunEventProcessor {
    protected final FlowRunService flowRunService;
    protected final StageRunService stageRunService;


    public TechnicalStageRunEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        this.flowRunService = flowRunService;
        this.stageRunService = stageRunService;
    }

    protected TechnicalStageRun getActualTechnicalRun(OrchestrableContext<?> orchestrableContext, String stageRunId){
        StageRun actual = orchestrableContext.getStageRunsById().get(stageRunId);
        if (actual == null) {
            throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
        } else if(actual instanceof TechnicalStageRun){
            return (TechnicalStageRun) actual;
        }else{
            throw new IllegalArgumentException("A non technical stage should not arrive in this process.");
        }
    }
}
