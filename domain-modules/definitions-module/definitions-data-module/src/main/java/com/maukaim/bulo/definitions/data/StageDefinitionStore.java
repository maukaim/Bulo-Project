package com.maukaim.bulo.definitions.data;

import com.maukaim.bulo.definitions.data.definition.StageDefinition;

import java.util.List;
import java.util.Set;

public interface StageDefinitionStore {
    StageDefinition addDefinition(StageDefinition definition);

    void addExecutor(String stageExecutorId, String id);

    StageDefinition getById(String id);

    List<StageDefinition> getAll();

    boolean removeExecutor(String executorId, String id);
}
