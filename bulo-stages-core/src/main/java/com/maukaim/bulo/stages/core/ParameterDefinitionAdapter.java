package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.io.definitions.ParameterDefinitionData;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;

public interface ParameterDefinitionAdapter {
    ParameterDefinition adapte(ParameterDefinitionData data);
}
