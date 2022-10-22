package com.maukaim.bulo.standalone.data.lifecycle.definitions;

import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.StageDefinitionStore;

import java.util.*;

public class MainDefinitionStore implements StageDefinitionStore {
    private Map<String, Set<String>> executorsByStageDefinitionId;
    private Map<String, StageDefinition> stageDefinitions;

    public MainDefinitionStore() {
        this.executorsByStageDefinitionId = new HashMap<>();
        this.stageDefinitions = new HashMap<>();
    }

    @Override
    public StageDefinition addDefinition(StageDefinition definition) {
        return this.stageDefinitions.put(definition.getId(), definition);
    }

    @Override
    public Set<String> addExecutor(String stageExecutorId, String stageId) {
        //TODO: add logic of publish? Shouldn't be done with the AddDefinition? Like the Event should be something having the new TechnicalStageDefinition AND the stageExecutor to be added? to be defined....
        return this.saveExecutor(stageExecutorId, stageId);
    }

    @Override
    public StageDefinition getById(String stageId) {
        return this.stageDefinitions.get(stageId);
    }

    @Override
    public List<StageDefinition> getAll() {
        return this.stageDefinitions.values().stream().toList();
    }


    public Set<String> saveExecutor(String stageExecutorId, String stageId) {
        this.executorsByStageDefinitionId.putIfAbsent(stageId, new HashSet<>());
        return this.executorsByStageDefinitionId.compute(stageId, (id, executorIds) -> {
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
        if (executorsByStageDefinitionId.containsKey(definitionId)) {
            Set<String> remainingExecutors = this.executorsByStageDefinitionId.compute(definitionId, (key, val) -> {
                if (val == null || val.isEmpty() || (val.size() == 1 && val.contains(executorId))) {
                    return null;
                }
                val.remove(executorId);
                return val;
            });
            if (remainingExecutors == null || remainingExecutors.isEmpty()) {
                this.stageDefinitions.remove(definitionId);
                return true;
            }
        }
        return false;
    }
}
