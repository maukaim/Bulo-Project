package com.maukaim.bulo.stages.persistence.adapters;

import com.maukaim.bulo.stages.io.models.stages.FunctionalStageDto;
import com.maukaim.bulo.stages.io.models.stages.TechnicalStageDto;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;

public interface StageAdapter {
    TechnicalStage adapte(TechnicalStageDto dto);

    FunctionalStage adapte(FunctionalStageDto dto);

    TechnicalStage adapte(String stageId, TechnicalStageDto dto);

    FunctionalStage adapte(String stageId, FunctionalStageDto dto);
}
