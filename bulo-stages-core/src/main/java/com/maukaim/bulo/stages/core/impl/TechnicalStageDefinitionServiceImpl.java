package com.maukaim.bulo.stages.core.impl;

import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.models.TechnicalStageDefinitionStore;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;

import java.util.List;

public class TechnicalStageDefinitionServiceImpl implements TechnicalStageDefinitionService {
    private final TechnicalStageDefinitionStore technicalStageDefinitionStore;

    public TechnicalStageDefinitionServiceImpl(TechnicalStageDefinitionStore technicalStageDefinitionStore) {
        this.technicalStageDefinitionStore = technicalStageDefinitionStore;
    }

    @Override
    public TechnicalStageDefinition put(TechnicalStageDefinition definition) {
        return this.technicalStageDefinitionStore.put(definition);
    }

    @Override
    public TechnicalStageDefinition remove(String definitionId) {
        TechnicalStageDefinition technicalStageDefinition = this.technicalStageDefinitionStore.getById(definitionId);
        return technicalStageDefinition == null ? null : this.technicalStageDefinitionStore.remove(technicalStageDefinition);
    }

    @Override
    public TechnicalStageDefinition getById(String definitionId) {
        return this.technicalStageDefinitionStore.getById(definitionId);
    }

    @Override
    public List<TechnicalStageDefinition> getAll() {
        return this.technicalStageDefinitionStore.getAll();
    }
}
