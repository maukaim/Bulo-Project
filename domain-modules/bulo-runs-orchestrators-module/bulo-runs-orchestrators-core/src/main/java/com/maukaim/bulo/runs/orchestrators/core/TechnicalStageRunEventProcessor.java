package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;

import java.util.Map;
import java.util.function.Function;

public abstract class TechnicalStageRunEventProcessor {
    protected final FlowRunService flowRunService;
    protected final StageRunService stageRunService;

    public TechnicalStageRunEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        this.flowRunService = flowRunService;
        this.stageRunService = stageRunService;
    }

    protected TechnicalStageRun getActualTechnicalRun(OrchestrableRunContext<?> orchestrableRunContext, String stageRunId){
        StageRun actualRun = getActualRun(orchestrableRunContext, stageRunId);
        if(actualRun instanceof TechnicalStageRun){
            return (TechnicalStageRun) actualRun;
        }else{
            throw new IllegalArgumentException("A non technical stage should not arrive in this process.");
        }
    }

    protected StageRun<?> getActualRun(OrchestrableRunContext<?> orchestrableRunContext, String stageRunId){
        StageRun actual = orchestrableRunContext.getStageRunsById().get(stageRunId);
        if (actual == null) {
            throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
        }
        return actual;
    }

    protected Map<String, StageRun> splitProcess(StageRun stageRun, Function<FunctionalStageRun, Map<String,StageRun>> functionalStageConsumer,
                                                 Function<TechnicalStageRun, Map<String,StageRun>> technicalStageRunConsumer){
        if(stageRun instanceof FunctionalStageRun)
            return functionalStageConsumer.apply((FunctionalStageRun) stageRun);
        else if (stageRun instanceof TechnicalStageRun)
            return technicalStageRunConsumer.apply((TechnicalStageRun) stageRun);
        else
            throw new UnsupportedOperationException("Does not support the following StageRun class: " + stageRun.getClass().getName());
    }
}
