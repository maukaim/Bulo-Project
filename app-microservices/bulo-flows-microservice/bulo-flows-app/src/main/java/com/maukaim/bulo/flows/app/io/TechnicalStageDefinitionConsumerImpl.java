package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.io.TechnicalStageDefinitionConsumer;
import com.maukaim.bulo.flows.io.definition.TechnicalStageDefinitionDto;
import com.maukaim.bulo.flows.io.events.TechnicalStageDefinitionEvent;

public class TechnicalStageDefinitionConsumerImpl implements TechnicalStageDefinitionConsumer {
    private final TechnicalStageDefinitionAdapter definitionAdapter;
    private final StageDefinitionStore stageDefinitionStore;

    public TechnicalStageDefinitionConsumerImpl(TechnicalStageDefinitionAdapter definitionAdapter, StageDefinitionStore stageDefinitionStore) {
        this.definitionAdapter = definitionAdapter;
        this.stageDefinitionStore = stageDefinitionStore;
    }

    @Override
    public void consume(TechnicalStageDefinitionEvent event) {
        System.out.println("Consume event: " + event);
        switch (event.getEventType()) {
            case UPDATE -> save(event.getTechnicalStageDefinition());
            case DELETE -> delete(event.getTechnicalStageDefinition());
        }
    }

    private void delete(TechnicalStageDefinitionDto technicalStageDefinition) {
        if(technicalStageDefinition != null && technicalStageDefinition.getTechnicalStageDefinitionId() != null){
            this.stageDefinitionStore.remove(technicalStageDefinition.getTechnicalStageDefinitionId());
        }
    }

    private void save(TechnicalStageDefinitionDto technicalStageDefinition) {
        StageDefinition definitionToSave = this.definitionAdapter.adapte(technicalStageDefinition);
        this.stageDefinitionStore.put(definitionToSave);
    }
}
