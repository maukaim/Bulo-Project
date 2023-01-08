package com.maukaim.bulo.standalone.data.lifecycle.definitions.sub.stores;

import com.maukaim.bulo.executors.data.StageDefinitionStore;
import com.maukaim.bulo.runners.api.models.StageDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.StageDefinitionInstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExecutorModuleDefinitionStore implements StageDefinitionStore {
    private final StageDefinitionInstructor definitionInstructor;
    private final Map<String, StageDefinition> stageDefinitionById;

    public ExecutorModuleDefinitionStore(StageDefinitionInstructor definitionInstructor,
                                         Map<String, StageDefinition> initialCache) {
        this.definitionInstructor = definitionInstructor;
        this.stageDefinitionById = new HashMap<>(initialCache == null ? Map.of() : initialCache);
    }

    @Override
    public StageDefinition getById(String definitionId) {
        return this.stageDefinitionById.get(definitionId);
    }

    @Override
    public StageDefinition put(StageDefinition stageDefinition) {
        this.stageDefinitionById.put(stageDefinition.getDefinitionId(), stageDefinition);
        StageDefinition definitionStored = this.stageDefinitionById.get(stageDefinition.getDefinitionId());
        this.definitionInstructor.create(definitionStored);
        return definitionStored;
    }

    @Override
    public List<StageDefinition> putAll(Collection<StageDefinition> stageDefinitions) {
        System.out.println("Save definition -> " + stageDefinitions);
        return stageDefinitions == null ? null : stageDefinitions.stream()
                .map(this::put)
                .collect(Collectors.toList());
    }

    @Override
    public StageDefinition remove(StageDefinition stage) {
        this.definitionInstructor.remove(stage.getDefinitionId());
        return stage;
    }
}
