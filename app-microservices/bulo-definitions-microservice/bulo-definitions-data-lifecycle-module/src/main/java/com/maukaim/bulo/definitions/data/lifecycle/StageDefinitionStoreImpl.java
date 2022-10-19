package com.maukaim.bulo.definitions.data.lifecycle;


import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.definitions.data.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.TechnicalStageDefinitionStore;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.commons.io.instructions.models.TechnicalStageDefinitionDto;

import java.time.Instant;
import java.util.*;

public class TechnicalStageDefinitionStoreImpl implements TechnicalStageDefinitionStore {
    private Map<String, Set<String>> executorsByTechnicalStageDefinitionId;
    private Map<String, TechnicalStageDefinition> technicalStageDefinitions;
    private final TechnicalStageDefinitionEventPublisher definitionEventPublisher;
    private final TechnicalStageDefinitionDtoAdapter definitionDtoAdapter;

    public TechnicalStageDefinitionStoreImpl(TechnicalStageDefinitionEventPublisher definitionEventPublisher, TechnicalStageDefinitionDtoAdapter definitionDtoAdapter) {
        this.definitionEventPublisher = definitionEventPublisher;
        this.definitionDtoAdapter = definitionDtoAdapter;
        this.executorsByTechnicalStageDefinitionId = new HashMap<>();
        this.technicalStageDefinitions = new HashMap<>();
    }

    @Override
    public TechnicalStageDefinition addDefinition(TechnicalStageDefinition definition) {
        boolean published = publish(definition, DefinitionEventType.UPDATE);
        return published ? definition : saveDefinition(definition);
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

    //TODO: Need to publish le removeExecutor ! Otherwise other registry wont see that the executor is not there anymore.
    // May need to merge the executor list to the model?
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
            if(remainingExecutors == null || remainingExecutors.isEmpty()){
                TechnicalStageDefinition technicalStageDefinition = this.technicalStageDefinitions.get(definitionId);
                boolean published = publish(technicalStageDefinition, DefinitionEventType.UPDATE);
                if(!published){
                    this.technicalStageDefinitions.remove(definitionId);
                }
                return true;
            }
        }
        return false;
    }

    public void remove(String technicalStageId) {
        this.technicalStageDefinitions.remove(technicalStageId);
        this.executorsByTechnicalStageDefinitionId.remove(technicalStageId);
    }

    public TechnicalStageDefinition saveDefinition(TechnicalStageDefinition definition) {
        return this.technicalStageDefinitions.put(definition.getTechnicalStageDefinitionId(), definition);
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
    private boolean publish(TechnicalStageDefinition definition, DefinitionEventType eventType) {
        TechnicalStageDefinitionDto dto = this.definitionDtoAdapter.adapte(definition);
        TechnicalStageDefinitionEvent event = new TechnicalStageDefinitionEvent(dto, eventType, Instant.now());
        return this.definitionEventPublisher.publish(event);
    }
}
