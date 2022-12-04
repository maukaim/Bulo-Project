package com.maukaim.bulo.standalone.data.lifecycle.definitions.sub.stores;

import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.UnsupportedDataMethodException;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.MainDefinitionStore;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.StageDefinitionAdapter;

public class FlowModuleDefinitionStore implements StageDefinitionStore {
    private final MainDefinitionStore mainDefinitionStore;
    private final StageDefinitionAdapter definitionAdapter;

    public FlowModuleDefinitionStore(MainDefinitionStore mainDefinitionStore, StageDefinitionAdapter definitionAdapter) {
        this.mainDefinitionStore = mainDefinitionStore;
        this.definitionAdapter = definitionAdapter;
    }

    @Override
    public StageDefinition getById(String definitionId) {
        com.maukaim.bulo.definitions.data.definition.StageDefinition definition = this.mainDefinitionStore.getById(definitionId);
        return this.definitionAdapter.adapteFlowModule(definition);
    }

    @Override
    public StageDefinition put(StageDefinition stageDefinition) {
        throw  UnsupportedDataMethodException.isSubStore();
    }

    @Override
    public StageDefinition remove(String stageDefinitionId) {
        throw UnsupportedDataMethodException.isSubStore();
    }
}
