package com.maukaim.bulo.definitions.registry.app.io;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.TechnicalStageDefinitionStoreImpl;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.commons.io.instructions.models.TechnicalStageDefinitionDto;

public class TechnicalStageDefinitionEventConsumerImpl implements TechnicalStageDefinitionEventConsumer {
    private final TechnicalStageDefinitionStoreImpl technicalStageDefinitionStore;
    private final TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter;

    public TechnicalStageDefinitionEventConsumerImpl(TechnicalStageDefinitionStoreImpl technicalStageDefinitionStore, TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter) {
        this.technicalStageDefinitionStore = technicalStageDefinitionStore;
        this.technicalStageDefinitionAdapter = technicalStageDefinitionAdapter;
    }

    @Override
    public void consume(TechnicalStageDefinitionEvent event) {
        System.out.println("Consume event: " + event);
        switch (event.getEventType()) {
            case UPDATE -> this.save(event.getTechnicalStageDefinition());
            case DELETE -> this.remove(event.getTechnicalStageDefinition().getTechnicalStageDefinitionId());
        }
    }

    private void save(TechnicalStageDefinitionDto dto) {
        TechnicalStageDefinition definition = this.technicalStageDefinitionAdapter.adapte(dto);
        this.technicalStageDefinitionStore.saveDefinition(definition);
    }

    private void remove(String technicalStageDefinitionId) {
        this.technicalStageDefinitionStore.remove(technicalStageDefinitionId);
    }
}

