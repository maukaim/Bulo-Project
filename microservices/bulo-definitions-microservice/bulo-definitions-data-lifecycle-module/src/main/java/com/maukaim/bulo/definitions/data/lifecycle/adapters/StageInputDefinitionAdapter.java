package com.maukaim.bulo.definitions.data.lifecycle.adapters;

import com.maukaim.bulo.definitions.data.StageInputDefinition;
import com.maukaim.bulo.definitions.io.models.StageInputDefinitionDto;

public interface StageInputDefinitionAdapter {
    StageInputDefinition adapte(StageInputDefinitionDto dto);
}
