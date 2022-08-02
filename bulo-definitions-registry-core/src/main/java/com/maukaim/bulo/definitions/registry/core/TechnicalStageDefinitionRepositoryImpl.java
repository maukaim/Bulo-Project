package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

import java.util.*;

public class TechnicalStageDefinitionRepositoryImpl implements TechnicalStageDefinitionRepository {
    private Map<String, Set<String>> executorsByTechnicalStageDefinitionId;
    private Map<String, TechnicalStageDefinition> technicalStageDefinitions;

    public TechnicalStageDefinitionRepositoryImpl() {
        this.executorsByTechnicalStageDefinitionId = new HashMap<>();
        this.technicalStageDefinitions = new HashMap<>();
    }

    @Override
    public void putDefinition(TechnicalStageDefinition definition) {
        this.technicalStageDefinitions.put(definition.getTechnicalStageDefinitionId(), definition);
    }

    @Override
    public void addExecutor(String stageExecutorId, String technicalStageId) {
        this.executorsByTechnicalStageDefinitionId.putIfAbsent(technicalStageId, new HashSet<>());
        this.executorsByTechnicalStageDefinitionId.compute(technicalStageId, (id, executorIds) -> {
            if (executorIds == null) {
                return new HashSet<>() {{
                    add(stageExecutorId);
                }};
            }
            executorIds.add(stageExecutorId);
            return executorIds;
        });
    }

    @Override
    public TechnicalStageDefinition get(String technicalStageDefinitionId) {
        return null;
    }

    @Override
    public List<TechnicalStageDefinition> getAll() {
        return this.technicalStageDefinitions.values().stream().toList();
    }
}
