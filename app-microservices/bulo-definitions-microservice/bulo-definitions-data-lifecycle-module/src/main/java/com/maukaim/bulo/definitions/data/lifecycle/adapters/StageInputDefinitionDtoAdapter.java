package com.maukaim.bulo.definitions.data.lifecycle.adapters;

import com.maukaim.bulo.definitions.data.StageInputDefinition;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;

public interface StageInputDefinitionDtoAdapter {
    StageInputDefinitionDto adapte(StageInputDefinition inputDefinition);
}
