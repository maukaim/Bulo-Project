package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.commons.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.executors.data.lifecycle.adapters.ParameterDefinitionDtoAdapter;
import com.maukaim.bulo.common.data.lifecycle.ParameterTypeDtoAdapter;
import com.maukaim.bulo.runners.api.models.ParameterDefinition;

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