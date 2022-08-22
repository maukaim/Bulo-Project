package com.maukaim.bulo.executor.data.management.adapters;

import com.maukaim.bulo.executor.core.api.models.ParameterDefinition;
import com.maukaim.bulo.executor.io.out.model.ParameterDefinitionDto;

public interface ParameterDefinitionDtoAdapter {
    ParameterDefinitionDto adapte(ParameterDefinition parameterDefinition);
}
