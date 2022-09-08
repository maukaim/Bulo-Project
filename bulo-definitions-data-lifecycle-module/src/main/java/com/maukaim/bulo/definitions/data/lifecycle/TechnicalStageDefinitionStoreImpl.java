package com.maukaim.bulo.definitions.data.lifecycle;


import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.TechnicalStageDefinitionStore;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.definitions.io.models.TechnicalStageDefinitionDto;

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
        boolean published = publish(definition);
        return published ? definition : saveDefinition(definition);
    }

    @Override
    public Set<String> addExecutor(String stageExecutorId, String technicalStageId) {
        //TODO: add logic of publish? Shouldn't be done with the AddDefinition? Like the Event should be something having the new TechnicalStageDefinition AND the stageExecutor to be added? to be defined....
        return this.saveExecutor(stageExecutorId, technicalStageId);
    }

    @Override
    public TechnicalStageDefinition get(String technicalStageDefinitionId) {
        return null;
    }

    @Override
    public List<TechnicalStageDefinition> getAll() {
        return this.technicalStageDefinitions.values().stream().toList();
    }

    public TechnicalStageDefinition saveDefinition(TechnicalStageDefinition definition) {
        return  this.technicalStageDefinitions.put(definition.getTechnicalStageDefinitionId(), definition);
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

    public void remove(String technicalStageId){
        this.technicalStageDefinitions.remove(technicalStageId);
        this.executorsByTechnicalStageDefinitionId.remove(technicalStageId);
    }

    private boolean publish(TechnicalStageDefinition definition){
        TechnicalStageDefinitionDto dto = this.definitionDtoAdapter.adapte(definition);
        TechnicalStageDefinitionEvent event = new TechnicalStageDefinitionEvent(dto, DefinitionEventType.UPDATE, Instant.now());
        return this.definitionEventPublisher.publish(event);
    }
}
