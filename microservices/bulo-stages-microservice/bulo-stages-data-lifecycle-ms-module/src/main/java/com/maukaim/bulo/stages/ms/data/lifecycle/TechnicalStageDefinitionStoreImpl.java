package com.maukaim.bulo.stages.ms.data.lifecycle;

import com.maukaim.bulo.stages.models.TechnicalStageDefinitionStore;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechnicalStageDefinitionStoreImpl implements TechnicalStageDefinitionStore {
    private final Map<String, TechnicalStageDefinition> technicalStageDefinitionById;

    public TechnicalStageDefinitionStoreImpl(Map<String, TechnicalStageDefinition> initialCache) {
        this.technicalStageDefinitionById = new HashMap<>(initialCache);
    }

    @Override
    public TechnicalStageDefinition getById(String definitionId) {
        return this.technicalStageDefinitionById.get(definitionId);
    }

    @Override
    public TechnicalStageDefinition put(TechnicalStageDefinition technicalStageDefinition) {
        return this.technicalStageDefinitionById.put(technicalStageDefinition.getId(), technicalStageDefinition);
    }

    @Override
    public TechnicalStageDefinition remove(TechnicalStageDefinition technicalStageDefinition) {
        return this.technicalStageDefinitionById.remove(technicalStageDefinition.getId());
    }

    @Override
    public List<TechnicalStageDefinition> getAll() {
        return this.technicalStageDefinitionById.values().stream().toList();
    }
}
