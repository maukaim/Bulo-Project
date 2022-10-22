package com.maukaim.bulo.definitions.io;

import com.maukaim.bulo.definitions.io.events.StageDefinitionEvent;

public interface TechnicalStageDefinitionEventConsumer {
    void consume(StageDefinitionEvent event);
}
