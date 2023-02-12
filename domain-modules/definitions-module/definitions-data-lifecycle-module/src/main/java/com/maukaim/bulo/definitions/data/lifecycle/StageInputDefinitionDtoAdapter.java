package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;

public interface StageInputDefinitionDtoAdapter {
    StageInputDefinitionDto adapte(StageInputDefinition inputDefinition);
}
