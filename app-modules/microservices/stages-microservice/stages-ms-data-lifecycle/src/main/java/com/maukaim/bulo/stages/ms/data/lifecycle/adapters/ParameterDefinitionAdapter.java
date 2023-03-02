package com.maukaim.bulo.stages.ms.data.lifecycle.adapters;

import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;

public interface ParameterDefinitionAdapter {
    ParameterDefinition adapte(ParameterDefinitionDto dto);
}
