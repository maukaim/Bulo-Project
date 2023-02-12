package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;

public interface StageOutputDefinitionAdapter {
    StageOutputDefinition adapte(StageOutputDefinitionDto dto);
}
