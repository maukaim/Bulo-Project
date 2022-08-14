package com.maukaim.bulo.executor.core;

import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;
import com.maukaim.bulo.executor.core.api.TechnicalStageRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Initialized with Embedded Runners.
 */
public class TechnicalStageRunnerManagerImpl implements TechnicalStageRunnerManager {
    private final Map<String, TechnicalStageRunner> runnersByTsdId;

    public TechnicalStageRunnerManagerImpl(Map<String, TechnicalStageRunner> defaultRunners) {
        this.runnersByTsdId = new HashMap<>(defaultRunners);
    }

    @Override
    public List<TechnicalStageDefinition> getAllDefinitions() {
        return runnersByTsdId.values().stream()
                .map(TechnicalStageRunner::getDefinition)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalStageRunner getByDefinitionId(String technicalStageDefinitionId) {
        return this.runnersByTsdId.get(technicalStageDefinitionId);
    }

    @Override
    public void add(String technicalStageDefinitionId, TechnicalStageRunner newRunner) {
        this.runnersByTsdId.putIfAbsent(technicalStageDefinitionId, newRunner);
    }

    @Override
    public TechnicalStageRunner drop(String technicalStageDefinitionId) {
        return this.runnersByTsdId.remove(technicalStageDefinitionId);
    }
}
