package com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.impl;

import com.maukaim.bulo.stages.models.stage.Parameter;
import com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.ParameterAdapter;

public class ParameterAdapterImpl implements ParameterAdapter {
    @Override
    public com.maukaim.bulo.flows.data.models.stage.Parameter adapteFlowModule(Parameter parameter) {
        return parameter == null ? null : new com.maukaim.bulo.flows.data.models.stage.Parameter(
                parameter.getValue(),
                parameter.getName(),
                parameter.getValueType()
        );
    }

    @Override
    public com.maukaim.bulo.executors.data.stages.Parameter adapteExecutorModule(Parameter parameter) {
        return parameter == null ? null : new com.maukaim.bulo.executors.data.stages.Parameter(
                parameter.getValue(),
                parameter.getName(),
                parameter.getValueType(),
                parameter.getAdditionalDetails()
        );
    }

    @Override
    public com.maukaim.bulo.definitions.data.stage.Parameter adapteDefinitionModule(Parameter parameter) {
        return parameter == null ? null : new com.maukaim.bulo.definitions.data.stage.Parameter(
                parameter.getValue(),
                parameter.getName(),
                parameter.getValueType()
        );
    }
}
