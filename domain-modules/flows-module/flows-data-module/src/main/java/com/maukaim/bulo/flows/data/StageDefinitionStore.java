package com.maukaim.bulo.flows.data;

import com.maukaim.bulo.flows.data.models.definition.StageDefinition;

public interface StageDefinitionStore {
    StageDefinition getById(String definitionId);
    StageDefinition put(StageDefinition stageDefinition);
    StageDefinition remove(String stageDefinitionId);
}
