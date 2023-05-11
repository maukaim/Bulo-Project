package com.maukaim.bulo.flows.ms.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;

public interface StageDefinitionAdapter {
    StageDefinition adapte(StageDefinitionDto dto);
}
