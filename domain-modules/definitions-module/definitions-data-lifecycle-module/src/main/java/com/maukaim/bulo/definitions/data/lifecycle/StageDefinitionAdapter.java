package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.io.definitions.client.models.StageDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;

public interface StageDefinitionAdapter {
    StageDefinition adapte(StageDefinitionDto dto);
}
