package com.maukaim.bulo.standalone.data.lifecycle.definitions.sub.stores;

import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.stages.models.StageDefinitionStore;
import com.maukaim.bulo.standalone.data.lifecycle.UnsupportedDataMethodException;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.MainDefinitionStore;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.StageDefinitionAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class StageModuleDefinitionStore implements StageDefinitionStore {
    private final MainDefinitionStore mainDefinitionStore;
    private final StageDefinitionAdapter definitionAdapter;

    public StageModuleDefinitionStore(MainDefinitionStore mainDefinitionStore, StageDefinitionAdapter definitionAdapter) {
        this.mainDefinitionStore = mainDefinitionStore;
        this.definitionAdapter = definitionAdapter;
    }

    @Override
    public com.maukaim.bulo.stages.models.definition.StageDefinition getById(String definitionId) {
        StageDefinition definition = this.mainDefinitionStore.getById(definitionId);
        return this.definitionAdapter.adapteStageModule(definition);
    }

    @Override
    public com.maukaim.bulo.stages.models.definition.StageDefinition put(com.maukaim.bulo.stages.models.definition.StageDefinition stageDefinition) {
        throw UnsupportedDataMethodException.isSubStore();
    }

    @Override
    public com.maukaim.bulo.stages.models.definition.StageDefinition remove(com.maukaim.bulo.stages.models.definition.StageDefinition stage) {
        throw UnsupportedDataMethodException.isSubStore();
    }

    @Override
    public List<com.maukaim.bulo.stages.models.definition.StageDefinition> getAll() {
        List<StageDefinition> allDefinitions = this.mainDefinitionStore.getAll();
        return allDefinitions == null ? List.of() : allDefinitions.stream()
                .map(definitionAdapter::adapteStageModule)
                .collect(Collectors.toList());
    }
}
