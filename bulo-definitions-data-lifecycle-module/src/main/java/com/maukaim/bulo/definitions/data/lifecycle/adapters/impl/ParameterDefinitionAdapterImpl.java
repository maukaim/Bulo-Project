package com.maukaim.bulo.definitions.data.lifecycle.adapters.impl;

import com.maukaim.bulo.definitions.data.ParameterDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.definitions.io.models.ParameterDefinitionDto;

public class ParameterDefinitionAdapterImpl implements ParameterDefinitionAdapter {
    @Override
    public ParameterDefinition adapte(ParameterDefinitionDto dto) {
        return new ParameterDefinition(
                dto.getName(),
                dto.getValueType(),
                dto.getHint(),
                dto.getDescription(),
                dto.isRequired()
        );
    }
}
