package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.io.definitions.client.dtos.StageOutputDefinitionDto;

public interface StageOutputDefinitionAdapter {
    StageOutputDefinition adapte(StageOutputDefinitionDto dto);
}
