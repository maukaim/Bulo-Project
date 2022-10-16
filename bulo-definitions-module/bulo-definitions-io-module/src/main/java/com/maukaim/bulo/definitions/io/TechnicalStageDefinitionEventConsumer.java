package com.maukaim.bulo.definitions.io;

import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionEvent;

public interface TechnicalStageDefinitionEventConsumer {
    void consume(TechnicalStageDefinitionEvent event);
}
