package com.maukaim.bulo.executors.core;

import com.maukaim.bulo.runners.api.StageRunner;
import com.maukaim.bulo.runners.api.models.StageDefinition;

import java.util.List;

public interface StageRunnerRegistry {
    List<StageDefinition> getAllDefinitions();

    StageRunner getByDefinitionId(String technicalStageDefinitionId);

    void add(String technicalStageDefinitionId, StageRunner newRunner);

    StageRunner drop(String technicalStageDefinitionId);
}
