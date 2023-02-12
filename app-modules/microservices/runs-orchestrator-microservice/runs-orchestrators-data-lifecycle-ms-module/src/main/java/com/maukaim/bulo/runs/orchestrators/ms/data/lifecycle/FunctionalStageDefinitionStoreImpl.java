package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageDefinitionStore;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;

import java.util.HashMap;
import java.util.Map;

public class FunctionalStageDefinitionStoreImpl implements FunctionalStageDefinitionStore {
    private final Map<String, FunctionalStageDefinition> cache;

    public FunctionalStageDefinitionStoreImpl(Map<String, FunctionalStageDefinition> initialCache) {
        this.cache = new HashMap<>(initialCache);
    }

    @Override
    public FunctionalStageDefinition getById(String definitionId) {
        return cache.get(definitionId);
    }

    @Override
    public void put(FunctionalStageDefinition definition) {
        cache.put(definition.getDefinitionId(), definition);
    }

    @Override
    public void remove(String definitionId) {
        cache.remove(definitionId);
    }
}
