package com.maukaim.bulo.definitions.data.lifecycle.adapters.impl;

import com.maukaim.bulo.definitions.data.ParameterDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.ParameterDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.io.models.ParameterDefinitionDto;

public class ParameterDefinitionDtoAdapterImpl implements ParameterDefinitionDtoAdapter {
    @Override
    public ParameterDefinitionDto adapte(ParameterDefinition parameterDefinition) {
        return new ParameterDefinitionDto(
                parameterDefinition.getName(),
                parameterDefinition.getValueType(),
                parameterDefinition.getHint(),
                parameterDefinition.getDescription(),
                parameterDefinition.isRequired()
        );
    }
}
