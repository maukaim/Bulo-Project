package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.ParameterDefinitionDtoAdapter;
import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;

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
