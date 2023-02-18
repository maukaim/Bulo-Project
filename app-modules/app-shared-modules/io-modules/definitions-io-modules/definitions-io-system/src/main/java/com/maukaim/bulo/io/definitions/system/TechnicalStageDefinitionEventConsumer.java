package com.maukaim.bulo.io.definitions.system;

import com.maukaim.bulo.io.definitions.system.events.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.system.events.StageDefinitionEvent;

public interface TechnicalStageDefinitionEventConsumer {
    void consume(StageDefinitionEvent event);

    void consume(ExecutorUpdateEvent event);
}
