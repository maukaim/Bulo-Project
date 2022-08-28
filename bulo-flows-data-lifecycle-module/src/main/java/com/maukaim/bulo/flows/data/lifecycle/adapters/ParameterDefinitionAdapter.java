package com.maukaim.bulo.flows.data.lifecycle.adapters;


import com.maukaim.bulo.flows.data.models.definition.ParameterDefinition;
import com.maukaim.bulo.flows.io.definition.ParameterDefinitionDto;

public interface ParameterDefinitionAdapter {
    ParameterDefinition adapte(ParameterDefinitionDto dto);
}
