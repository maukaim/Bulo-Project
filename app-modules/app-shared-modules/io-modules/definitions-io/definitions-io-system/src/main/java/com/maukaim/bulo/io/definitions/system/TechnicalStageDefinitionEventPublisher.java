package com.maukaim.bulo.io.definitions.system;

import com.maukaim.bulo.io.definitions.system.events.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.system.events.StageDefinitionEvent;

public interface TechnicalStageDefinitionEventPublisher {
    boolean publish(StageDefinitionEvent event);
    boolean publish(ExecutorUpdateEvent event);
}
