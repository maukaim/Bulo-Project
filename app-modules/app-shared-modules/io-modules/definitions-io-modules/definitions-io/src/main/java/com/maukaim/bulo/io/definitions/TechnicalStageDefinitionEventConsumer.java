package com.maukaim.bulo.io.definitions;

import com.maukaim.bulo.io.definitions.events.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.events.StageDefinitionEvent;

public interface TechnicalStageDefinitionEventConsumer {
    void consume(StageDefinitionEvent event);

    void consume(ExecutorUpdateEvent event);
}
