package com.maukaim.bulo.stages.core.impl;

import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.stages.models.StageDefinitionStore;
import com.maukaim.bulo.stages.models.definition.StageDefinition;

import java.util.List;

public class StageDefinitionServiceImpl implements StageDefinitionService {
    private final StageDefinitionStore stageDefinitionStore;

    public StageDefinitionServiceImpl(StageDefinitionStore stageDefinitionStore) {
        this.stageDefinitionStore = stageDefinitionStore;
    }

    @Override
    public StageDefinition put(StageDefinition definition) {
        return this.stageDefinitionStore.put(definition);
    }

    @Override
    public StageDefinition remove(String definitionId) {
        StageDefinition stageDefinition = this.stageDefinitionStore.getById(definitionId);
        return stageDefinition == null ? null : this.stageDefinitionStore.remove(stageDefinition);
    }

    @Override
    public StageDefinition getById(String definitionId) {
        return this.stageDefinitionStore.getById(definitionId);
    }

    @Override
    public List<StageDefinition> getAll() {
        return this.stageDefinitionStore.getAll();
    }
}
