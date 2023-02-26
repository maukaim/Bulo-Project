package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageDefinitionEventConsumer;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.StageDefinitionAdapter;

public class StageDefinitionEventConsumerImpl implements StageDefinitionEventConsumer {
    private final StageDefinitionAdapter stageDefinitionAdapter;
    private final StageDefinitionService stageDefinitionService;

    public StageDefinitionEventConsumerImpl(StageDefinitionAdapter stageDefinitionAdapter, StageDefinitionService stageDefinitionService) {
        this.stageDefinitionAdapter = stageDefinitionAdapter;
        this.stageDefinitionService = stageDefinitionService;
    }

    @Override
    public void consume(StageDefinitionEvent event) {
        System.out.println("Consume event : " + event);
        switch (event.getEventType()) {
            case UPDATE -> this.saveDefinition(event.getStageDefinition());
            case DELETE ->
                    this.stageDefinitionService.remove(event.getStageDefinition().getDefinitionId()).getDefinitionId();
        }
    }

    private StageDefinition saveDefinition(StageDefinitionDto dto) {
        StageDefinition stageDefinition = this.stageDefinitionAdapter.adapte(dto);
        return this.stageDefinitionService.put(stageDefinition);
    }
}
