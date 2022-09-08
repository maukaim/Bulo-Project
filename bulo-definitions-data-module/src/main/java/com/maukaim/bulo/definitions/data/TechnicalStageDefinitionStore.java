package com.maukaim.bulo.definitions.data;

import java.util.List;
import java.util.Set;

public interface TechnicalStageDefinitionStore {
    TechnicalStageDefinition addDefinition(TechnicalStageDefinition definition);

    Set<String> addExecutor(String stageExecutorId, String technicalStageDefinitionId);

    TechnicalStageDefinition get(String technicalStageDefinitionId);

    List<TechnicalStageDefinition> getAll();
}
