package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters;

import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;

public interface ParameterDefinitionAdapter {
    com.maukaim.bulo.flows.data.models.definition.ParameterDefinition adapteFlowModule(ParameterDefinition parameterDefinition);
    com.maukaim.bulo.stages.models.definition.ParameterDefinition adapterStageModule(ParameterDefinition parameterDefinition);
    ParameterDefinition adapteFromExecutorModule(com.maukaim.bulo.runners.api.models.ParameterDefinition parameterDefinition);
}
