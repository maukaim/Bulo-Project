package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.io.definitions.shared.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.runners.api.models.StageInputDefinition;

public interface StageInputDefinitionDtoAdapter {
    StageInputDefinitionDto adapte(StageInputDefinition inputDefinition);
}
