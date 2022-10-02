package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters;

import com.maukaim.bulo.definitions.data.StageInputDefinition;

public interface StageInputDefinitionAdapter {
    com.maukaim.bulo.flows.data.models.definition.StageInputDefinition adapteFlowModule(StageInputDefinition inputDefinition);
    StageInputDefinition adapteFromExecutorModule(com.maukaim.bulo.executors.data.models.StageInputDefinition inputDefinition);
}
