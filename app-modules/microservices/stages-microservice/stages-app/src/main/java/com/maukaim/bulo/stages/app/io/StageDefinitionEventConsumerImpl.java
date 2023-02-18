package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.io.stages.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.io.stages.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.io.stages.models.definitions.StageDefinitionDto;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;

public class StageDefinitionEventConsumerImpl implements TechnicalStageDefinitionEventConsumer {
    private final TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter;
    private final StageDefinitionService stageDefinitionService;

    public StageDefinitionEventConsumerImpl(TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter, StageDefinitionService stageDefinitionService) {
        this.technicalStageDefinitionAdapter = technicalStageDefinitionAdapter;
        this.stageDefinitionService = stageDefinitionService;
    }

    @Override
    public void consume(TechnicalStageDefinitionEvent event) {
        System.out.println("Consume event : " + event);
        switch (event.getEventType()) {
            case UPDATE -> this.saveDefinition(event.getTechnicalStageDefinition());
            case DELETE -> this.stageDefinitionService.remove(event.getTechnicalStageDefinition().getDefinitionId());
        };
    }

    private StageDefinition saveDefinition(StageDefinitionDto dto) {
        StageDefinition stageDefinition = this.technicalStageDefinitionAdapter.adapte(dto);
        return this.stageDefinitionService.put(stageDefinition);
    }
}
