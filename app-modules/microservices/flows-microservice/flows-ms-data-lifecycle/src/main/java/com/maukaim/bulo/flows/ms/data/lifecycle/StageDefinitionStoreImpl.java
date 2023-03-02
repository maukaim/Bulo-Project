package com.maukaim.bulo.flows.ms.data.lifecycle;

import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;

import java.util.HashMap;
import java.util.Map;

public class StageDefinitionStoreImpl implements StageDefinitionStore {
    private Map<String, StageDefinition> definitionCache;

    public StageDefinitionStoreImpl(Map<String, StageDefinition> initialCache) {
        this.definitionCache = new HashMap<>(initialCache);
    }

    @Override
    public StageDefinition getById(String definitionId) {
        return this.definitionCache.get(definitionId);
    }

    @Override
    public StageDefinition put(StageDefinition stageDefinition) {
        System.out.println("Saving definition: " + stageDefinition);
        return this.definitionCache.put(stageDefinition.getStageDefinitionId(), stageDefinition);
    }

    @Override
    public StageDefinition remove(String stageDefinitionId) {
        return this.definitionCache.remove(stageDefinitionId);
    }
}
