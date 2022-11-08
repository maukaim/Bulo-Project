package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;

public interface FunctionalStageDefinitionService {
    FunctionalStageDefinition getById(String definitionId);
    void put(FunctionalStageDefinition definition);
    void remove(String definitionId);
}
