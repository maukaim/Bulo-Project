package com.maukaim.bulo.executors.data;

import com.maukaim.bulo.executors.data.models.StageDefinition;

import java.util.Collection;
import java.util.List;

public interface StageDefinitionStore {
    StageDefinition getById(String definitionId);
    StageDefinition put(StageDefinition stageDefinition);
    List<StageDefinition> putAll(Collection<StageDefinition> stageDefinitions);

    StageDefinition remove(StageDefinition stage);

    List<StageDefinition> getAll();
}
