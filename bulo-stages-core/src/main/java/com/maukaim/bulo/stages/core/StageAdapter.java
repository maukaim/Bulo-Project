package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.io.stages.FunctionalStageData;
import com.maukaim.bulo.io.stages.TechnicalStageData;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;

public interface StageAdapter {
    TechnicalStage adapte(TechnicalStageData data);
    FunctionalStage adapte(FunctionalStageData data);

    TechnicalStage adapte(String stageId, TechnicalStageData data);
    FunctionalStage adapte(String stageId, FunctionalStageData data);

}
