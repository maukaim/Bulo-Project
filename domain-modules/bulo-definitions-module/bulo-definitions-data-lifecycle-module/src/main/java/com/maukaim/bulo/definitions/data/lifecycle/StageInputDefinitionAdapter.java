package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;

public interface StageInputDefinitionAdapter {
    StageInputDefinition adapte(StageInputDefinitionDto dto);
}
