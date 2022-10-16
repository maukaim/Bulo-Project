package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.executors.data.models.ParameterDefinition;

public interface ParameterDefinitionDtoAdapter {
    ParameterDefinitionDto adapte(ParameterDefinition parameterDefinition);
}
