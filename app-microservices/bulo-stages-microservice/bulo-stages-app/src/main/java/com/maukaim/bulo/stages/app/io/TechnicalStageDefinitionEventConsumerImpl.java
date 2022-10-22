package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.io.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.stages.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.stages.io.models.definitions.StageDefinitionDto;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;

public class TechnicalStageDefinitionEventConsumerImpl implements TechnicalStageDefinitionEventConsumer {
    private final TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter;
    private final TechnicalStageDefinitionService technicalStageDefinitionService;

    public TechnicalStageDefinitionEventConsumerImpl(TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter, TechnicalStageDefinitionService technicalStageDefinitionService) {
        this.technicalStageDefinitionAdapter = technicalStageDefinitionAdapter;
        this.technicalStageDefinitionService = technicalStageDefinitionService;
    }

    @Override
    public void consume(TechnicalStageDefinitionEvent event) {
        System.out.println("Consume event : " + event);
        switch (event.getEventType()) {
            case UPDATE -> this.saveDefinition(event.getTechnicalStageDefinition());
            case DELETE -> this.technicalStageDefinitionService.remove(event.getTechnicalStageDefinition().getId());
        };
    }

    private StageDefinition saveDefinition(StageDefinitionDto dto) {
        StageDefinition stageDefinition = this.technicalStageDefinitionAdapter.adapte(dto);
        return this.technicalStageDefinitionService.put(stageDefinition);
    }
}
