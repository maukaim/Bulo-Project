package com.maukaim.bulo.stages.data.lifecycle;

import com.maukaim.bulo.io.stages.models.stages.FunctionalStageDto;
import com.maukaim.bulo.io.stages.models.stages.TechnicalStageDto;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;

public interface StageAdapter {
    TechnicalStage adapte(TechnicalStageDto dto);

    FunctionalStage adapte(FunctionalStageDto dto);

    TechnicalStage adapte(String stageId, TechnicalStageDto dto);

    FunctionalStage adapte(String stageId, FunctionalStageDto dto);
}
