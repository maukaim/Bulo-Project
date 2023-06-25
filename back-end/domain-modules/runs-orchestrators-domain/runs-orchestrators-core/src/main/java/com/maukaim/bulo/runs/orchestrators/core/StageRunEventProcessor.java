package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;

import java.util.Map;
import java.util.function.Function;

public abstract class StageRunEventProcessor {
    protected final FlowRunService flowRunService;
    protected final StageRunService stageRunService;
    protected final FunctionalStageRunFactory functionalStageRunFactory;
    protected final TechnicalStageRunFactory technicalStageRunFactory;

    public StageRunEventProcessor(FlowRunService flowRunService,
                                  StageRunService stageRunService,
                                  FunctionalStageRunFactory functionalStageRunFactory,
                                  TechnicalStageRunFactory technicalStageRunFactory){
        this.flowRunService = flowRunService;
        this.stageRunService = stageRunService;
        this.functionalStageRunFactory = functionalStageRunFactory;
        this.technicalStageRunFactory = technicalStageRunFactory;
    }

    protected StageRun<?> getActualRun(String stageRunId) {
        StageRun<?> actual = this.stageRunService.getById(stageRunId);
        if (actual == null) {
            throw new IllegalArgumentException("This stage id was not requested to run under this flowRun.");
        }
        return actual;
    }

    protected Map<String, StageRun<?>> splitProcess(StageRun<?> stageRun, Function<FunctionalStageRun, Map<String, StageRun<?>>> functionalStageConsumer,
                                                    Function<TechnicalStageRun, Map<String, StageRun<?>>> technicalStageRunConsumer) {
        if (stageRun instanceof FunctionalStageRun)
            return functionalStageConsumer.apply((FunctionalStageRun) stageRun);
        else if (stageRun instanceof TechnicalStageRun)
            return technicalStageRunConsumer.apply((TechnicalStageRun) stageRun);
        else
            throw new UnsupportedOperationException("Does not support the following StageRun class: " + stageRun.getClass().getName());
    }
}
