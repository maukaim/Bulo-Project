package com.maukaim.bulo.definitions.ms.data.lifecycle;


import com.maukaim.bulo.io.definitions.shared.DefinitionEventType;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageDefinitionDto;
import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionDtoAdapter;
import com.maukaim.bulo.io.definitions.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.io.definitions.events.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.events.StageDefinitionEvent;

import java.time.Instant;
import java.util.*;

import static com.maukaim.bulo.io.definitions.shared.DefinitionEventType.UPDATE;

public class StageDefinitionStoreImpl implements StageDefinitionStore {
    private Map<String, Set<String>> executorsByStageDefinitionId;
    private Map<String, StageDefinition> stageDefinitions;
    private final TechnicalStageDefinitionEventPublisher definitionEventPublisher;
    private final StageDefinitionDtoAdapter definitionDtoAdapter;

    public StageDefinitionStoreImpl(TechnicalStageDefinitionEventPublisher definitionEventPublisher, StageDefinitionDtoAdapter definitionDtoAdapter) {
        this.definitionEventPublisher = definitionEventPublisher;
        this.definitionDtoAdapter = definitionDtoAdapter;
        this.executorsByStageDefinitionId = new HashMap<>();
        this.stageDefinitions = new HashMap<>();
    }

    @Override
    public StageDefinition addDefinition(StageDefinition definition) {
        boolean published = publish(definition, UPDATE);
        return published ? definition : saveDefinition(definition);
    }

    @Override
    public void addExecutor(String stageExecutorId, String technicalStageId) {
        boolean published = this.definitionEventPublisher.publish(new ExecutorUpdateEvent(stageExecutorId, technicalStageId, UPDATE, Instant.now()));
        if (!published) {
            this.saveExecutor(stageExecutorId, technicalStageId);
        }
    }

    @Override
    public StageDefinition getById(String definitionId) {
        return this.stageDefinitions.get(definitionId);
    }

    @Override
    public List<StageDefinition> getAll() {
        return this.stageDefinitions.values().stream().toList();
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
                StageDefinition stageDefinition = this.stageDefinitions.get(definitionId);
                boolean published = publish(stageDefinition, UPDATE);
                if (!published) {
                    this.stageDefinitions.remove(definitionId);
                }
                return true;
            }
        }
        return false;
    }

    public void remove(String definitionId) {
        this.stageDefinitions.remove(definitionId);
        this.executorsByStageDefinitionId.remove(definitionId);
    }

    public StageDefinition saveDefinition(StageDefinition definition) {
        this.stageDefinitions.put(definition.getDefinitionId(), definition);
        return definition;
    }

    public Set<String> saveExecutor(String stageExecutorId, String definitionId) {
        return this.executorsByStageDefinitionId.compute(definitionId, (id, executorIds) -> {
            if (executorIds == null) {
                return new HashSet<>() {{
                    add(stageExecutorId);
                }};
            }
            executorIds.add(stageExecutorId);
            return executorIds;
        });
    }

    private boolean publish(StageDefinition definition, DefinitionEventType eventType) {
        StageDefinitionDto dto = this.definitionDtoAdapter.adapte(definition);
        StageDefinitionEvent event = new StageDefinitionEvent(dto, eventType, Instant.now());
        return this.definitionEventPublisher.publish(event);
    }
}
