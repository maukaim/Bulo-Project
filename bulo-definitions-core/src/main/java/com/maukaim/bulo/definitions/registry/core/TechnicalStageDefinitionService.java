package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;

import java.util.List;

public interface TechnicalStageDefinitionService {
    void register(String stageExecutorId, TechnicalStageDefinition definition);

    TechnicalStageDefinition get(String definitionId);

    List<TechnicalStageDefinition> getAll();
}
