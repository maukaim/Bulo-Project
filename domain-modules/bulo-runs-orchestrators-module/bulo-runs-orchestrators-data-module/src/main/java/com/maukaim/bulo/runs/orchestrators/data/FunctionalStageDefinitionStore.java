package com.maukaim.bulo.runs.orchestrators.data;

import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;

public interface FunctionalStageDefinitionStore {
    FunctionalStageDefinition getById(String definitionId);
    void put(FunctionalStageDefinition definition);
    void remove(String definitionId);
}
