package com.maukaim.bulo.definitions.registry.io;

import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

public interface TechnicalStageDefinitionPublisher {
    void publish(DefinitionEventType eventType, TechnicalStageDefinition record);
}
