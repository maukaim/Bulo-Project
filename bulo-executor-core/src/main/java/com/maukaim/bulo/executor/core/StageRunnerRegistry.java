package com.maukaim.bulo.executor.core;

import com.maukaim.bulo.executor.core.api.StageRunner;
import com.maukaim.bulo.executor.core.api.models.StageDefinition;

import java.util.List;

public interface StageRunnerRegistry {
    List<StageDefinition> getAllDefinitions();

    StageRunner getByDefinitionId(String technicalStageDefinitionId);

    void add(String technicalStageDefinitionId, StageRunner newRunner);

    StageRunner drop(String technicalStageDefinitionId);
}
