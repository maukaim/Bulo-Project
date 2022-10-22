package com.maukaim.bulo.stages.ms.data.lifecycle.adapters;

import com.maukaim.bulo.stages.io.models.definitions.StageDefinitionDto;
import com.maukaim.bulo.stages.models.definition.StageDefinition;

public interface TechnicalStageDefinitionAdapter {
    StageDefinition adapte(StageDefinitionDto dto);
}
