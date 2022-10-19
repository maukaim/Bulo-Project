package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.data.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.technical.TechnicalStageDefinition;

import java.util.List;

public interface TechnicalStageDefinitionService {
    void register(FunctionalStageDefinition functionalStageDefinition);
    void register(String stageExecutorId, TechnicalStageDefinition definition);
    void unregister(String stageExecutorId, String definitionId);

    com.maukaim.bulo.definitions.data.StageDefinition get(String definitionId);

    List<com.maukaim.bulo.definitions.data.StageDefinition> getAll();
}
