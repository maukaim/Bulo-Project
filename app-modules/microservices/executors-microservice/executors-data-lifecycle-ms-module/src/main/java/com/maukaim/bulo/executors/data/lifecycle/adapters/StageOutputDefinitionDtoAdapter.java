package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.io.definitions.shared.instructions.models.StageOutputDefinitionDto;
import com.maukaim.bulo.runners.api.models.StageOutputDefinition;

public interface StageOutputDefinitionDtoAdapter {
    StageOutputDefinitionDto adapte(StageOutputDefinition outputDefinition);
}
