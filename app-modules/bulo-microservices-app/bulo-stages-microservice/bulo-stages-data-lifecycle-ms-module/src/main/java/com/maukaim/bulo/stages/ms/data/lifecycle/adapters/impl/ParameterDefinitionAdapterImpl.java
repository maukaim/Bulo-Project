package com.maukaim.bulo.stages.ms.data.lifecycle.adapters.impl;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.commons.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.stages.io.models.definitions.ParameterDefinitionDto;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.common.data.lifecycle.ParameterTypeAdapter;

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
