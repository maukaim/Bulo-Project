package com.maukaim.bulo.standalone.data.lifecycle.definitions.sub.stores;

import com.maukaim.bulo.stages.models.TechnicalStageDefinitionStore;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.UnsupportedDataMethodException;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.MainDefinitionStore;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.TechnicalStageDefinitionAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class StageModuleDefinitionStore implements TechnicalStageDefinitionStore {
    private final MainDefinitionStore mainDefinitionStore;
    private final TechnicalStageDefinitionAdapter definitionAdapter;

    public StageModuleDefinitionStore(MainDefinitionStore mainDefinitionStore, TechnicalStageDefinitionAdapter definitionAdapter) {
        this.mainDefinitionStore = mainDefinitionStore;
        this.definitionAdapter = definitionAdapter;
    }

    @Override
    public TechnicalStageDefinition getById(String definitionId) {
        com.maukaim.bulo.definitions.data.TechnicalStageDefinition definition = this.mainDefinitionStore.getById(definitionId);
        return this.definitionAdapter.adapteStageModule(definition);
    }

    @Override
    public TechnicalStageDefinition put(TechnicalStageDefinition technicalStageDefinition) {
        throw UnsupportedDataMethodException.isSubStore();
    }

    @Override
    public TechnicalStageDefinition remove(TechnicalStageDefinition stage) {
        throw UnsupportedDataMethodException.isSubStore();
    }

    @Override
    public List<TechnicalStageDefinition> getAll() {
        List<com.maukaim.bulo.definitions.data.TechnicalStageDefinition> allDefinitions = this.mainDefinitionStore.getAll();
        return allDefinitions == null ? List.of() : allDefinitions.stream()
                .map(definitionAdapter::adapteStageModule)
                .collect(Collectors.toList());
    }
}
