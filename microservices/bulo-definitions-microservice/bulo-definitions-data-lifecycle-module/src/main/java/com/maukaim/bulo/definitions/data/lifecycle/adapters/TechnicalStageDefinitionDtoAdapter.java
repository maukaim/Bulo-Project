package com.maukaim.bulo.definitions.data.lifecycle.adapters;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.io.models.TechnicalStageDefinitionDto;

public interface TechnicalStageDefinitionDtoAdapter {
    TechnicalStageDefinitionDto adapte(TechnicalStageDefinition technicalStageDefinition);
}
