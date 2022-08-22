package com.maukaim.bulo.executor.core.impl;

import com.maukaim.bulo.executor.core.StageRunnerRegistry;
import com.maukaim.bulo.executor.core.api.StageRunner;
import com.maukaim.bulo.executor.core.api.models.StageDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class StageRunnerRegistryImpl implements StageRunnerRegistry {
    private final Map<String, StageRunner> runnersByTsdId;

    public StageRunnerRegistryImpl(Map<String, StageRunner> defaultRunners) {
        this.runnersByTsdId = new HashMap<>(defaultRunners);
    }

    @Override
    public List<StageDefinition> getAllDefinitions() {
        return runnersByTsdId.values().stream()
                .map(StageRunner::getDefinition)
                .collect(Collectors.toList());
    }

    @Override
    public StageRunner getByDefinitionId(String technicalStageDefinitionId) {
        return this.runnersByTsdId.get(technicalStageDefinitionId);
    }

    @Override
    public void add(String technicalStageDefinitionId, StageRunner newRunner) {
        this.runnersByTsdId.putIfAbsent(technicalStageDefinitionId, newRunner);
    }

    @Override
    public StageRunner drop(String technicalStageDefinitionId) {
        return this.runnersByTsdId.remove(technicalStageDefinitionId);
    }
}
