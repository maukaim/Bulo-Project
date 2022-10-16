package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters;

import com.maukaim.bulo.definitions.data.StageOutputDefinition;

public interface StageOutputDefinitionAdapter {
    com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition adapteFlowModule(StageOutputDefinition outputDefinition);
    StageOutputDefinition adapteFromExecutorModule(com.maukaim.bulo.executors.data.models.StageOutputDefinition outputDefinition);
}
