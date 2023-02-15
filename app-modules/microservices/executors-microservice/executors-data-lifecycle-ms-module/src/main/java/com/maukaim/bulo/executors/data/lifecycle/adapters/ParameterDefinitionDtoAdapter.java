package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.io.definitions.shared.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.runners.api.models.ParameterDefinition;

public interface ParameterDefinitionDtoAdapter {
    ParameterDefinitionDto adapte(ParameterDefinition parameterDefinition);
}
