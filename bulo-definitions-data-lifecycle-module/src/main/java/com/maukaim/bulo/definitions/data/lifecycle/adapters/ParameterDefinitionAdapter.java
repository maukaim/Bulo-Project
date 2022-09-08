package com.maukaim.bulo.definitions.data.lifecycle.adapters;

import com.maukaim.bulo.definitions.data.ParameterDefinition;
import com.maukaim.bulo.definitions.io.models.ParameterDefinitionDto;

public interface ParameterDefinitionAdapter {
    ParameterDefinition adapte(ParameterDefinitionDto dto);
}
