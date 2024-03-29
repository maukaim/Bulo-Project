package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl;

import com.maukaim.bulo.flows.data.models.definition.ParameterDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.ParameterDefinitionAdapter;

public class ParameterDefinitionAdapterImpl implements ParameterDefinitionAdapter {
    @Override
    public ParameterDefinition adapteFlowModule(com.maukaim.bulo.definitions.data.definition.ParameterDefinition parameterDefinition) {
        return parameterDefinition == null ? null : new ParameterDefinition(
                parameterDefinition.getName(),
                parameterDefinition.getParameterType()
        );
    }

    @Override
    public com.maukaim.bulo.stages.models.definition.ParameterDefinition adapterStageModule(com.maukaim.bulo.definitions.data.definition.ParameterDefinition parameterDefinition) {
        return parameterDefinition == null ? null : new com.maukaim.bulo.stages.models.definition.ParameterDefinition(
                parameterDefinition.getName(),
                parameterDefinition.getParameterType(),
                parameterDefinition.getHint(),
                parameterDefinition.getDescription()
        );
    }

    @Override
    public com.maukaim.bulo.definitions.data.definition.ParameterDefinition adapteFromExecutorModule(com.maukaim.bulo.runners.api.models.ParameterDefinition parameterDefinition) {
        return parameterDefinition == null ? null : new com.maukaim.bulo.definitions.data.definition.ParameterDefinition(
                parameterDefinition.getName(),
                parameterDefinition.getParameterType(),
                parameterDefinition.getHint(),
                parameterDefinition.getDescription()
        );
    }
}
