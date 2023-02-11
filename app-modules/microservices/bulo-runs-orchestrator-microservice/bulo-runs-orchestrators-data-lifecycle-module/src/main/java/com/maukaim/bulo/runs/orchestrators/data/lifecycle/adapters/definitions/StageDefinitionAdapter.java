package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions;

import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.StageDefinitionDto;

public interface StageDefinitionAdapter {
    FunctionalStageDefinition adapte(StageDefinitionDto dto);
}
