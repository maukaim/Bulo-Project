package com.maukaim.bulo.definitions.registry.app.io;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionCreateInstructionConsumer;
import com.maukaim.bulo.commons.io.instructions.TechnicalStageDefinitionCreateInstruction;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionService;

public class TechnicalStageDefinitionDeclarationEventConsumerImpl implements TechnicalStageDefinitionCreateInstructionConsumer {
    private final TechnicalStageDefinitionService technicalStageDefinitionService;
    private final TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter;

    public TechnicalStageDefinitionDeclarationEventConsumerImpl(TechnicalStageDefinitionService technicalStageDefinitionService, TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter) {
        this.technicalStageDefinitionService = technicalStageDefinitionService;
        this.technicalStageDefinitionAdapter = technicalStageDefinitionAdapter;
    }

    @Override
    public void consume(TechnicalStageDefinitionCreateInstruction event) {
        System.out.println("Consume event: " + event);
        TechnicalStageDefinition definition = this.technicalStageDefinitionAdapter.adapte(event.getTechnicalStageDefinition());
        this.technicalStageDefinitionService.register(event.getStageExecutorId(), definition);
    }
}
