package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.impl;

import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.StageInputDefinitionAdapter;

public class StageInputDefinitionAdapterImpl implements StageInputDefinitionAdapter {
    @Override
    public StageInputDefinition adapteFlowModule(com.maukaim.bulo.definitions.data.StageInputDefinition inputDefinition) {
        return inputDefinition == null ? null : new StageInputDefinition(
                inputDefinition.isCanBeMultiple(),
                inputDefinition.isRequired(),
                inputDefinition.getTypeId()
        );
    }

    @Override
    public com.maukaim.bulo.definitions.data.StageInputDefinition adapteFromExecutorModule(com.maukaim.bulo.executors.data.models.StageInputDefinition inputDefinition) {
        return inputDefinition == null ? null : new com.maukaim.bulo.definitions.data.StageInputDefinition(
                inputDefinition.isCanBeMultiple(),
                inputDefinition.isRequired(),
                inputDefinition.getTypeId()
        ) ;
    }
}
