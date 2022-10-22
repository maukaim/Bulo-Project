package com.maukaim.bulo.standalone.data.lifecycle.stages.adapters;

import com.maukaim.bulo.stages.models.stage.Parameter;

public interface ParameterAdapter {
    com.maukaim.bulo.flows.data.models.stage.Parameter adapteFlowModule(Parameter parameter);
    com.maukaim.bulo.executors.data.stages.Parameter adapteExecutorModule(Parameter parameter);
    com.maukaim.bulo.definitions.data.stage.Parameter adapteDefinitionModule(Parameter parameter);
}
