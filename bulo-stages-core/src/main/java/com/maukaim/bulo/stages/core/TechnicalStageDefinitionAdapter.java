package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.io.definitions.TechnicalStageDefinitionData;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;

public interface TechnicalStageDefinitionAdapter {
    TechnicalStageDefinition adapte(TechnicalStageDefinitionData data);
}
