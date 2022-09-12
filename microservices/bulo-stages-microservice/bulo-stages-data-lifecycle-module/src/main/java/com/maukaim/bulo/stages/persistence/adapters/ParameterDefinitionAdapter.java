package com.maukaim.bulo.stages.persistence.adapters;

import com.maukaim.bulo.stages.io.models.definitions.ParameterDefinitionDto;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;

public interface ParameterDefinitionAdapter {
    ParameterDefinition adapte(ParameterDefinitionDto dto);
}
