package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.io.definitions.client.models.StageInputDefinitionDto;

public interface StageInputDefinitionAdapter {
    StageInputDefinition adapte(StageInputDefinitionDto dto);
}
