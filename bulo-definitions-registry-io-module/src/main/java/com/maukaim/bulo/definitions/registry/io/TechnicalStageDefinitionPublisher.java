package com.maukaim.bulo.definitions.registry.io;

import com.maukaim.bulo.definitions.registry.io.model.TSDEventType;
import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

public interface TechnicalStageDefinitionPublisher {
    void publish(TSDEventType eventType, TechnicalStageDefinition record);
}
