package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl;

import com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.StageOutputDefinitionAdapter;

public class StageOutputDefinitionAdapterImpl implements StageOutputDefinitionAdapter {
    @Override
    public StageOutputDefinition adapteFlowModule(com.maukaim.bulo.definitions.data.definition.StageOutputDefinition outputDefinition) {
        return outputDefinition == null ? null : new StageOutputDefinition(outputDefinition.getType());
    }

    @Override
    public com.maukaim.bulo.definitions.data.definition.StageOutputDefinition adapteFromExecutorModule(com.maukaim.bulo.runners.api.models.StageOutputDefinition outputDefinition) {
        return outputDefinition == null ? null : new com.maukaim.bulo.definitions.data.definition.StageOutputDefinition(outputDefinition.getType());
    }
}
