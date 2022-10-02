package com.maukaim.bulo.standalone.data.lifecycle.definitions;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.TechnicalStageDefinitionStore;

import java.util.*;

public class MainDefinitionStore implements TechnicalStageDefinitionStore {
    private Map<String, Set<String>> executorsByTechnicalStageDefinitionId;
    private Map<String, TechnicalStageDefinition> technicalStageDefinitions;

    public MainDefinitionStore() {
        this.executorsByTechnicalStageDefinitionId = new HashMap<>();
        this.technicalStageDefinitions = new HashMap<>();
    }

    @Override
    public TechnicalStageDefinition addDefinition(TechnicalStageDefinition definition) {
        return this.technicalStageDefinitions.put(definition.getTechnicalStageDefinitionId(), definition);
    }

    @Override
    public Set<String> addExecutor(String stageExecutorId, String technicalStageId) {
        //TODO: add logic of publish? Shouldn't be done with the AddDefinition? Like the Event should be something having the new TechnicalStageDefinition AND the stageExecutor to be added? to be defined....
        return this.saveExecutor(stageExecutorId, technicalStageId);
    }

    @Override
    public TechnicalStageDefinition getById(String technicalStageDefinitionId) {
        return this.technicalStageDefinitions.get(technicalStageDefinitionId);
    }

    @Override
    public List<TechnicalStageDefinition> getAll() {
        return this.technicalStageDefinitions.values().stream().toList();
    }


    public Set<String> saveExecutor(String stageExecutorId, String technicalStageId) {
        this.executorsByTechnicalStageDefinitionId.putIfAbsent(technicalStageId, new HashSet<>());
        return this.executorsByTechnicalStageDefinitionId.compute(technicalStageId, (id, executorIds) -> {
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
    public boolean removeExecutor(String executorId, String definitionId) {
        if (executorsByTechnicalStageDefinitionId.containsKey(definitionId)) {
            Set<String> remainingExecutors = this.executorsByTechnicalStageDefinitionId.compute(definitionId, (key, val) -> {
                if (val == null || val.isEmpty() || (val.size() == 1 && val.contains(executorId))) {
                    return null;
                }
                val.remove(executorId);
                return val;
            });
            if (remainingExecutors == null || remainingExecutors.isEmpty()) {
                this.technicalStageDefinitions.remove(definitionId);
                return true;
            }
        }
        return false;
    }
}
