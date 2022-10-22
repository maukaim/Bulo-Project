package com.maukaim.bulo.stages.ms.data.lifecycle;

import com.maukaim.bulo.stages.models.StageDefinitionStore;
import com.maukaim.bulo.stages.models.definition.StageDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StageDefinitionStoreImpl implements StageDefinitionStore {
    private final Map<String, StageDefinition> technicalStageDefinitionById;

    public StageDefinitionStoreImpl(Map<String, StageDefinition> initialCache) {
        this.technicalStageDefinitionById = new HashMap<>(initialCache);
    }

    @Override
    public StageDefinition getById(String definitionId) {
        return this.technicalStageDefinitionById.get(definitionId);
    }

    @Override
    public StageDefinition put(StageDefinition stageDefinition) {
        return this.technicalStageDefinitionById.put(stageDefinition.getId(), stageDefinition);
    }

    @Override
    public StageDefinition remove(StageDefinition stageDefinition) {
        return this.technicalStageDefinitionById.remove(stageDefinition.getId());
    }

    @Override
    public List<StageDefinition> getAll() {
        return this.technicalStageDefinitionById.values().stream().toList();
    }
}
