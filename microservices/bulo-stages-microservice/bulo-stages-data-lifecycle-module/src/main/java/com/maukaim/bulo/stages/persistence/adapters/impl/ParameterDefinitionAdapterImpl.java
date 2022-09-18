package com.maukaim.bulo.stages.persistence.adapters.impl;

import com.maukaim.bulo.stages.io.models.definitions.ParameterDefinitionDto;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;
import com.maukaim.bulo.stages.persistence.adapters.ParameterDefinitionAdapter;

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