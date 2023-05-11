package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;
import com.maukaim.bulo.runners.api.models.ParameterDefinition;

public interface ParameterDefinitionDtoAdapter {
    ParameterDefinitionDto adapte(ParameterDefinition parameterDefinition);
}
