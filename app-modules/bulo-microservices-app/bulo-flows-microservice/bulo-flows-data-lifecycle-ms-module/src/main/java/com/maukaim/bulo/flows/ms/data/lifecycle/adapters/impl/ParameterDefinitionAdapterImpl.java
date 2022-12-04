package com.maukaim.bulo.flows.ms.data.lifecycle.adapters.impl;


import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.flows.data.models.definition.ParameterDefinition;
import com.maukaim.bulo.flows.io.definition.ParameterDefinitionDto;

public class ParameterDefinitionAdapterImpl implements ParameterDefinitionAdapter {
    @Override
    public ParameterDefinition adapte(ParameterDefinitionDto dto) {
        return new ParameterDefinition(
                dto.getName(),
                dto.getValueType(),
                dto.isRequired()
        );
    }
}
