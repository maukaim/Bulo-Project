package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;

import java.util.List;

public interface StageDefinitionService {
    void register(FunctionalStageDefinition functionalStageDefinition);
    void register(String stageExecutorId, TechnicalStageDefinition definition);
    void unregister(String stageExecutorId, String definitionId);

    StageDefinition get(String definitionId);

    List<StageDefinition> getAll();
}
