package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.common.data.lifecycle.ParameterTypeAdapter;
import com.maukaim.bulo.commons.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.ParameterDefinitionAdapter;
import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;

public class ParameterDefinitionAdapterImpl implements ParameterDefinitionAdapter {
    private ParameterTypeAdapter parameterTypeAdapter;

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
