package com.maukaim.bulo.definitions.data.lifecycle.adapters;

import com.maukaim.bulo.definitions.data.StageDefinition;
import com.maukaim.bulo.commons.io.instructions.models.technical.TechnicalStageDefinitionDto;

public interface TechnicalStageDefinitionDtoAdapter {
    TechnicalStageDefinitionDto adapte(StageDefinition stageDefinition);
}
