package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageDefinitionAdapter;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.io.flows.StageDefinitionConsumer;
import com.maukaim.bulo.io.flows.definition.stageDefinitionDto;
import com.maukaim.bulo.io.flows.events.StageDefinitionEvent;

public class StageDefinitionConsumerImpl implements StageDefinitionConsumer {
    private final StageDefinitionAdapter definitionAdapter;
    private final StageDefinitionStore stageDefinitionStore;

    public StageDefinitionConsumerImpl(StageDefinitionAdapter definitionAdapter, StageDefinitionStore stageDefinitionStore) {
        this.definitionAdapter = definitionAdapter;
        this.stageDefinitionStore = stageDefinitionStore;
    }

    @Override
    public void consume(StageDefinitionEvent event) {
        System.out.println("Consume event: " + event);
        switch (event.getEventType()) {
            case UPDATE -> save(event.getStageDefinition());
            case DELETE -> delete(event.getStageDefinition());
        }
    }

    private void delete(stageDefinitionDto stageDefinition) {
        if(stageDefinition != null && stageDefinition.getDefinitionId() != null){
            this.stageDefinitionStore.remove(stageDefinition.getDefinitionId());
        }
    }

    private void save(stageDefinitionDto stageDefinition) {
        StageDefinition definitionToSave = this.definitionAdapter.adapte(stageDefinition);
        this.stageDefinitionStore.put(definitionToSave);
    }
}
