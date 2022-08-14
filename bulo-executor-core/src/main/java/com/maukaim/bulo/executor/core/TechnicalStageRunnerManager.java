package com.maukaim.bulo.executor.core;

import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;
import com.maukaim.bulo.executor.core.api.TechnicalStageRunner;

import java.util.List;

public interface TechnicalStageRunnerManager {
    List<TechnicalStageDefinition> getAllDefinitions();
    TechnicalStageRunner getByDefinitionId(String technicalStageDefinitionId);
    void add(String technicalStageDefinitionId, TechnicalStageRunner newRunner);
    TechnicalStageRunner drop(String technicalStageDefinitionId);
}
