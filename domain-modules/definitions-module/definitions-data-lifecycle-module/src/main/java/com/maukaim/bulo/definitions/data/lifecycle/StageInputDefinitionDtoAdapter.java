package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.io.definitions.client.dtos.StageInputDefinitionDto;

public interface StageInputDefinitionDtoAdapter {
    StageInputDefinitionDto adapte(StageInputDefinition inputDefinition);
}
