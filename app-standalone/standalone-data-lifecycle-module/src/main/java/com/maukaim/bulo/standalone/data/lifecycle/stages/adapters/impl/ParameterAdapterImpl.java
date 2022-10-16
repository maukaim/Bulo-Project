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
    public com.maukaim.bulo.executors.data.stages.Parameter adapteExecutorModule(com.maukaim.bulo.stages.models.stage.Parameter parameter) {
        return parameter == null ? null : new com.maukaim.bulo.executors.data.stages.Parameter(
                parameter.getValue(),
                parameter.getName(),
                parameter.getValueType(),
                parameter.getAdditionalDetails()
        );
    }
}
