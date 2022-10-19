package com.maukaim.bulo.definitions.data.lifecycle.adapters;

import com.maukaim.bulo.definitions.data.technical.TechnicalStageDefinition;
import com.maukaim.bulo.commons.io.instructions.models.technical.TechnicalStageDefinitionDto;

public interface TechnicalStageDefinitionAdapter {
    TechnicalStageDefinition adapte(TechnicalStageDefinitionDto dto);
}
