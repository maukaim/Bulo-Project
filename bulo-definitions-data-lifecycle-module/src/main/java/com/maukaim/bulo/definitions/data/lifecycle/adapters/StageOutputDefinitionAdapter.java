package com.maukaim.bulo.definitions.data.lifecycle.adapters;

import com.maukaim.bulo.definitions.data.StageOutputDefinition;
import com.maukaim.bulo.definitions.io.models.StageOutputDefinitionDto;

public interface StageOutputDefinitionAdapter {
    StageOutputDefinition adapte(StageOutputDefinitionDto dto);
}
