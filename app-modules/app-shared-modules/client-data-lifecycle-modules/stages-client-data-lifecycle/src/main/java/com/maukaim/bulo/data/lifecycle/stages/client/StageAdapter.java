package com.maukaim.bulo.data.lifecycle.stages.client;

import com.maukaim.bulo.io.stages.client.model.FunctionalStageDto;
import com.maukaim.bulo.io.stages.client.model.TechnicalStageDto;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;

public interface StageAdapter {
    TechnicalStage adapte(TechnicalStageDto dto);

    FunctionalStage adapte(FunctionalStageDto dto);

    TechnicalStage adapte(String stageId, TechnicalStageDto dto);

    FunctionalStage adapte(String stageId, FunctionalStageDto dto);
}
