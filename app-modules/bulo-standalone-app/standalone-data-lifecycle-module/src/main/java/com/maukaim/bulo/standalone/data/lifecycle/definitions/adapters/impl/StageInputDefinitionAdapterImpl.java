package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl;

import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.StageInputDefinitionAdapter;

public class StageInputDefinitionAdapterImpl implements StageInputDefinitionAdapter {
    @Override
    public StageInputDefinition adapteFlowModule(com.maukaim.bulo.definitions.data.definition.StageInputDefinition inputDefinition) {
        return inputDefinition == null ? null : new StageInputDefinition(inputDefinition.getType());
    }

    @Override
    public com.maukaim.bulo.definitions.data.definition.StageInputDefinition adapteFromExecutorModule(com.maukaim.bulo.runners.api.models.StageInputDefinition inputDefinition) {
        return inputDefinition == null ? null : new com.maukaim.bulo.definitions.data.definition.StageInputDefinition(inputDefinition.getType());
    }
}
