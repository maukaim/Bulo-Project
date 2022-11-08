package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageDefinitionStore;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;

public class FunctionalStageDefinitionServiceImpl implements FunctionalStageDefinitionService {
    private final FunctionalStageDefinitionStore functionalStageDefinitionStore;

    public FunctionalStageDefinitionServiceImpl(FunctionalStageDefinitionStore functionalStageDefinitionStore) {
        this.functionalStageDefinitionStore = functionalStageDefinitionStore;
    }

    @Override
    public FunctionalStageDefinition getById(String definitionId) {
        return functionalStageDefinitionStore.getById(definitionId);
    }

    @Override
    public void put(FunctionalStageDefinition definition) {
        functionalStageDefinitionStore.put(definition);
    }

    @Override
    public void remove(String definitionId) {
        functionalStageDefinitionStore.remove(definitionId);
    }
}
