package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.flows.core.DefinitionService;
import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;

public class DefinitionServiceImpl implements DefinitionService {
    private final StageDefinitionStore stageDefinitionStore;

    public DefinitionServiceImpl(StageDefinitionStore stageDefinitionStore) {
        this.stageDefinitionStore = stageDefinitionStore;
    }

    @Override
    public StageDefinition getById(String definitionId) {
        return this.stageDefinitionStore.getById(definitionId);
    }
}
