package com.maukaim.bulo.data.lifecycle.definitions.client;

import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;

public interface StageDefinitionAdapter {
    StageDefinition adapte(StageDefinitionDto dto);
}
