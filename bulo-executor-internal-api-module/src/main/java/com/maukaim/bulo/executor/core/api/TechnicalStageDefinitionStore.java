package com.maukaim.bulo.executor.core.api;

import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;

import java.util.Collection;
import java.util.List;

public interface TechnicalStageDefinitionStore {
    TechnicalStageDefinition getById(String definitionId);
    TechnicalStageDefinition put(TechnicalStageDefinition technicalStageDefinition);
    List<TechnicalStageDefinition> putAll(Collection<TechnicalStageDefinition> technicalStageDefinitions);

    TechnicalStageDefinition remove(TechnicalStageDefinition stage);

    List<TechnicalStageDefinition> getAll();
}
