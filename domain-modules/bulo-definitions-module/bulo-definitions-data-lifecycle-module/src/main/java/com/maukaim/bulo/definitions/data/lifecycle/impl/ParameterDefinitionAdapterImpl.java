package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.ParameterDefinitionAdapter;
import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;

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
