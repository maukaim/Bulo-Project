package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.commons.io.instructions.models.technical.TechnicalStageDefinitionDto;
import com.maukaim.bulo.runners.api.models.StageDefinition;

public interface StageDefinitionDtoAdapter {
    TechnicalStageDefinitionDto adapte(StageDefinition data);
}
