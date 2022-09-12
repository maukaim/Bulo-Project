package com.maukaim.bulo.definitions.registry.app.io;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionDeclarationEventConsumer;
import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionDeclarationEvent;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionService;

public class TechnicalStageDefinitionDeclarationEventConsumerImpl implements TechnicalStageDefinitionDeclarationEventConsumer {
    private final TechnicalStageDefinitionService technicalStageDefinitionService;
    private final TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter;

    public TechnicalStageDefinitionDeclarationEventConsumerImpl(TechnicalStageDefinitionService technicalStageDefinitionService, TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter) {
        this.technicalStageDefinitionService = technicalStageDefinitionService;
        this.technicalStageDefinitionAdapter = technicalStageDefinitionAdapter;
    }

    @Override
    public void consume(TechnicalStageDefinitionDeclarationEvent event) {
        TechnicalStageDefinition definition = this.technicalStageDefinitionAdapter.adapte(event.getTechnicalStageDefinition());
        this.technicalStageDefinitionService.register(event.getStageExecutorId(), definition);
    }
}
