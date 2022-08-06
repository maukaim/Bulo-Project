package com.maukaim.bulo.stages.core.definitions;

import com.maukaim.bulo.io.definitions.TechnicalStageDefinitionData;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.models.TechnicalStageDefinitionStore;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;

import java.util.List;

public class TechnicalStageDefinitionServiceImpl implements TechnicalStageDefinitionService {
    private final TechnicalStageDefinitionStore technicalStageDefinitionStore;
    private final TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter;

    public TechnicalStageDefinitionServiceImpl(TechnicalStageDefinitionStore technicalStageDefinitionStore, TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter) {
        this.technicalStageDefinitionStore = technicalStageDefinitionStore;
        this.technicalStageDefinitionAdapter = technicalStageDefinitionAdapter;
    }

    @Override
    public TechnicalStageDefinition put(TechnicalStageDefinitionData data) {
        TechnicalStageDefinition technicalStageDefinition = this.technicalStageDefinitionAdapter.adapte(data);
        return this.technicalStageDefinitionStore.put(technicalStageDefinition);
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
