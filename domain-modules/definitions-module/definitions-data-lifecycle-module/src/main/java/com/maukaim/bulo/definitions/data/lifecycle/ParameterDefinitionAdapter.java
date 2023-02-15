package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.io.definitions.shared.instructions.models.ParameterDefinitionDto;

public interface ParameterDefinitionAdapter {
    ParameterDefinition adapte(ParameterDefinitionDto dto);
}
