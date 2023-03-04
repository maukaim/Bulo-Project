package com.maukaim.bulo.data.lifecycle.definitions.client.impl;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.data.lifecycle.data.types.client.ParameterTypeAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.ParameterDefinitionAdapter;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;

public class ParameterDefinitionAdapterImpl implements ParameterDefinitionAdapter {
    private final ParameterTypeAdapter parameterTypeAdapter;

    public ParameterDefinitionAdapterImpl(ParameterTypeAdapter parameterTypeAdapter) {
        this.parameterTypeAdapter = parameterTypeAdapter;
    }

    @Override
    public ParameterDefinition adapte(ParameterDefinitionDto dto) {
        return new ParameterDefinition(
                dto.getName(),
                resolve(dto.getParameterType()),
                dto.getHint(),
                dto.getDescription()
        );
    }

    private ParameterType resolve(ParameterTypeDto dto) {
        return this.parameterTypeAdapter.adapte(dto);
    }
}
