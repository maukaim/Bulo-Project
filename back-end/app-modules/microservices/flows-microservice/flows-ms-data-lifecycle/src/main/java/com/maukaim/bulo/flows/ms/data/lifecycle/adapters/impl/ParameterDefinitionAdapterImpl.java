package com.maukaim.bulo.flows.ms.data.lifecycle.adapters.impl;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.data.lifecycle.data.types.client.ParameterTypeAdapter;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.flows.data.models.definition.ParameterDefinition;
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
                resolve(dto.getParameterType())
        );
    }

    private ParameterType resolve(ParameterTypeDto dto) {
        return this.parameterTypeAdapter.adapte(dto);
    }

}
