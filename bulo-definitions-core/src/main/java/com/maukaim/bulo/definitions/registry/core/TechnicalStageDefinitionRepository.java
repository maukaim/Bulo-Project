package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

import java.util.List;

public interface TechnicalStageDefinitionRepository {
    void putDefinition(TechnicalStageDefinition definition);

    void addExecutor(String stageExecutorId, String technicalStageDefinitionId);

    TechnicalStageDefinition get(String technicalStageDefinitionId);

    List<TechnicalStageDefinition> getAll();
}
