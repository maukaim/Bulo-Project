package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions;

import com.maukaim.bulo.io.definitions.client.dtos.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;

public interface StageDefinitionAdapter {
    FunctionalStageDefinition adapte(FunctionalStageDefinitionDto dto);
}
