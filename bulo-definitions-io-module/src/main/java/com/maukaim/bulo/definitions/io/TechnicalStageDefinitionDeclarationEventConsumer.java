package com.maukaim.bulo.definitions.io;

import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionDeclarationEvent;

public interface TechnicalStageDefinitionDeclarationEventConsumer {
    void consume(TechnicalStageDefinitionDeclarationEvent event);
}
