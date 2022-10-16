package com.maukaim.bulo.executors.core;

import com.maukaim.bulo.executors.data.StageRunner;
import com.maukaim.bulo.executors.data.models.StageDefinition;

import java.util.List;

public interface StageRunnerRegistry {
    List<StageDefinition> getAllDefinitions();

    StageRunner getByDefinitionId(String technicalStageDefinitionId);

    void add(String technicalStageDefinitionId, StageRunner newRunner);

    StageRunner drop(String technicalStageDefinitionId);
}
