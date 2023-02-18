package com.maukaim.bulo.flows.ms.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.definition.ParameterDefinition;
import com.maukaim.bulo.io.flows.system.definition.ParameterDefinitionDto;

public interface ParameterDefinitionAdapter {
    ParameterDefinition adapte(ParameterDefinitionDto dto);
}
