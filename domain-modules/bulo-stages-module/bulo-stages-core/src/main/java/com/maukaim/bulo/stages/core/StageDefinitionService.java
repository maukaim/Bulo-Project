package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.stages.models.definition.StageDefinition;

import java.util.List;

public interface StageDefinitionService {
    StageDefinition put(StageDefinition definition);

    StageDefinition remove(String definitionId);

    StageDefinition getById(String definitionId);

    List<StageDefinition> getAll();
}
