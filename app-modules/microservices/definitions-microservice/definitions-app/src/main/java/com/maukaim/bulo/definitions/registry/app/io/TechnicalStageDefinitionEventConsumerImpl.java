package com.maukaim.bulo.definitions.registry.app.io;

import com.maukaim.bulo.io.definitions.shared.instructions.models.StageDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionAdapter;
import com.maukaim.bulo.io.definitions.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.io.definitions.events.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.events.StageDefinitionEvent;
import com.maukaim.bulo.definitions.ms.data.lifecycle.StageDefinitionStoreImpl;

public class TechnicalStageDefinitionEventConsumerImpl implements TechnicalStageDefinitionEventConsumer {
    private final StageDefinitionStoreImpl technicalStageDefinitionStore;
    private final StageDefinitionAdapter stageDefinitionAdapter;

    public TechnicalStageDefinitionEventConsumerImpl(StageDefinitionStoreImpl technicalStageDefinitionStore, StageDefinitionAdapter stageDefinitionAdapter) {
        this.technicalStageDefinitionStore = technicalStageDefinitionStore;
        this.stageDefinitionAdapter = stageDefinitionAdapter;
    }

    @Override
    public void consume(StageDefinitionEvent event) {
        System.out.println("Consume event: " + event);
        switch (event.getEventType()) {
            case UPDATE -> this.save(event.getStageDefinition());
            case DELETE -> this.remove(event.getStageDefinition().getDefinitionId());
        }
    }

    @Override
    public void consume(ExecutorUpdateEvent event) {
        System.out.println("Consume event: " + event);
        switch (event.getEventType()) {
            case UPDATE -> this.technicalStageDefinitionStore.saveExecutor(
                    event.getExecutorId(),
                    event.getDefinitionId()
            );
            case DELETE -> this.technicalStageDefinitionStore.removeExecutor(
                    event.getExecutorId(),
                    event.getDefinitionId()
            );
        }
    }

    private void save(StageDefinitionDto dto) {
        StageDefinition definition = this.stageDefinitionAdapter.adapte(dto);
        this.technicalStageDefinitionStore.saveDefinition(definition);
    }

    private void remove(String technicalStageDefinitionId) {
        this.technicalStageDefinitionStore.remove(technicalStageDefinitionId);
    }
}

