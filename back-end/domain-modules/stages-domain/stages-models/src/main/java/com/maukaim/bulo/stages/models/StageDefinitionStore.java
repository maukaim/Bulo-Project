package com.maukaim.bulo.stages.models;

import com.maukaim.bulo.stages.models.definition.StageDefinition;

import java.util.List;

public interface StageDefinitionStore {
    StageDefinition getById(String definitionId);

    StageDefinition put(StageDefinition stageDefinition);

    StageDefinition remove(StageDefinition stage);

    List<StageDefinition> getAll();
}
