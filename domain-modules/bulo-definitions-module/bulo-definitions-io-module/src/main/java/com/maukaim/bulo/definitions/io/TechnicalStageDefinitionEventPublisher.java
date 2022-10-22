package com.maukaim.bulo.definitions.io;

import com.maukaim.bulo.definitions.io.events.StageDefinitionEvent;

public interface TechnicalStageDefinitionEventPublisher {
    boolean publish(StageDefinitionEvent event);
}
