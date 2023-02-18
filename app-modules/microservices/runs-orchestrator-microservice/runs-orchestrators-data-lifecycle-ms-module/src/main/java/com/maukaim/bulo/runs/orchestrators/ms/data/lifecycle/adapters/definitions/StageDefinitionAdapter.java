package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions;

import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.io.runs.orchestrators.system.models.definition.StageDefinitionDto;

public interface StageDefinitionAdapter {
    FunctionalStageDefinition adapte(StageDefinitionDto dto);
}
