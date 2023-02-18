package com.maukaim.bulo.io.definitions;

import com.maukaim.bulo.io.definitions.events.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.events.StageDefinitionEvent;

public interface TechnicalStageDefinitionEventPublisher {
    boolean publish(StageDefinitionEvent event);
    boolean publish(ExecutorUpdateEvent event);
}
