package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.executors.data.lifecycle.adapters.ParameterDefinitionDtoAdapter;
import com.maukaim.bulo.executors.data.models.ParameterDefinition;
import com.maukaim.bulo.executors.io.out.model.ParameterDefinitionDto;

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
