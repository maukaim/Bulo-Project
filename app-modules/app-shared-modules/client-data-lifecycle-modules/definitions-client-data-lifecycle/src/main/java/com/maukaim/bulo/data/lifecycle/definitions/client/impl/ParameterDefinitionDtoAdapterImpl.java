package com.maukaim.bulo.data.lifecycle.definitions.client.impl;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.data.lifecycle.data.types.client.ParameterTypeDtoAdapter;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.data.lifecycle.definitions.client.ParameterDefinitionDtoAdapter;

public class ParameterDefinitionDtoAdapterImpl implements ParameterDefinitionDtoAdapter {
    private final ParameterTypeDtoAdapter parameterTypeDtoAdapter;

    public ParameterDefinitionDtoAdapterImpl(ParameterTypeDtoAdapter parameterTypeDtoAdapter) {
        this.parameterTypeDtoAdapter = parameterTypeDtoAdapter;
    }

    @Override
    public ParameterDefinitionDto adapte(ParameterDefinition parameterDefinition) {
        return new ParameterDefinitionDto(
                parameterDefinition.getName(),
                resolve(parameterDefinition.getParameterType()),
                parameterDefinition.getHint(),
                parameterDefinition.getDescription()
        );
    }

    private ParameterTypeDto resolve(ParameterType parameterType) {
        return this.parameterTypeDtoAdapter.adapte(parameterType);
    }
}
