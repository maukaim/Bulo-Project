package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.io.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.stages.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.stages.io.models.definitions.TechnicalStageDefinitionDto;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;
import com.maukaim.bulo.stages.persistence.adapters.TechnicalStageDefinitionAdapter;

public class TechnicalStageDefinitionEventConsumerImpl implements TechnicalStageDefinitionEventConsumer {
    private final TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter;
    private final TechnicalStageDefinitionService technicalStageDefinitionService;

    public TechnicalStageDefinitionEventConsumerImpl(TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter, TechnicalStageDefinitionService technicalStageDefinitionService) {
        this.technicalStageDefinitionAdapter = technicalStageDefinitionAdapter;
        this.technicalStageDefinitionService = technicalStageDefinitionService;
    }

    @Override
    public void consume(TechnicalStageDefinitionEvent event) {
        switch (event.getEventType()) {
            case UPDATE -> this.saveDefinition(event.getTechnicalStageDefinition());
            case DELETE -> this.technicalStageDefinitionService.remove(event.getTechnicalStageDefinition().getId());
        };
    }

    private TechnicalStageDefinition saveDefinition(TechnicalStageDefinitionDto dto) {
        TechnicalStageDefinition technicalStageDefinition = this.technicalStageDefinitionAdapter.adapte(dto);
        return this.technicalStageDefinitionService.put(technicalStageDefinition);
    }
}
