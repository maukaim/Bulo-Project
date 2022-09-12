package com.maukaim.bulo.stages.persistence.adapters;

import com.maukaim.bulo.stages.io.models.definitions.TechnicalStageDefinitionDto;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;

public interface TechnicalStageDefinitionAdapter {
    TechnicalStageDefinition adapte(TechnicalStageDefinitionDto dto);
}
