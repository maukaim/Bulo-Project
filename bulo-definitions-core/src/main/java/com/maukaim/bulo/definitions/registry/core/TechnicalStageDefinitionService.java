package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

import java.util.List;

public interface TechnicalStageDefinitionService {
    void add(String stageExecutorId, TechnicalStageDefinition definition);

    TechnicalStageDefinition get(String definitionId);

    List<TechnicalStageDefinition> getAll();
}
