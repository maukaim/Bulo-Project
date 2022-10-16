package com.maukaim.bulo.standalone.data.lifecycle.definitions.sub.stores;

import com.maukaim.bulo.executors.data.StageDefinitionStore;
import com.maukaim.bulo.executors.data.models.StageDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.StageDefinitionInstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExecutorModuleDefinitionStore implements StageDefinitionStore {
    private final StageDefinitionInstructor definitionInstructor;
    private final Map<String, StageDefinition> technicalStageDefinitionById;

    public ExecutorModuleDefinitionStore(StageDefinitionInstructor definitionInstructor,
                                         Map<String, StageDefinition> initialCache) {
        this.definitionInstructor = definitionInstructor;
        this.technicalStageDefinitionById = new HashMap<>(initialCache == null ? Map.of() : initialCache);
    }

    @Override
    public StageDefinition getById(String definitionId) {
        return this.technicalStageDefinitionById.get(definitionId);
    }

    @Override
    public StageDefinition put(StageDefinition stageDefinition) {
        this.technicalStageDefinitionById.put(stageDefinition.getTechnicalStageDefinitionId(), stageDefinition);
        StageDefinition definitionStored = this.technicalStageDefinitionById.get(stageDefinition.getTechnicalStageDefinitionId());
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
        this.definitionInstructor.remove(stage.getTechnicalStageDefinitionId());
        return stage;
    }

    @Override
    public List<StageDefinition> getAll() {
        return this.technicalStageDefinitionById.values().stream().toList();
    }
}
