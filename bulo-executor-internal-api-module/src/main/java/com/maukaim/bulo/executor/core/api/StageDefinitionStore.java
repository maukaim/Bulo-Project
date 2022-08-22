package com.maukaim.bulo.executor.core.api;

import com.maukaim.bulo.executor.core.api.models.StageDefinition;

import java.util.Collection;
import java.util.List;

public interface StageDefinitionStore {
    StageDefinition getById(String definitionId);
    StageDefinition put(StageDefinition stageDefinition);
    List<StageDefinition> putAll(Collection<StageDefinition> stageDefinitions);

    StageDefinition remove(StageDefinition stage);

    List<StageDefinition> getAll();
}
