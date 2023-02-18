package com.maukaim.bulo.io.stages.system;

import com.maukaim.bulo.io.stages.system.events.TechnicalStageDefinitionEvent;

public interface TechnicalStageDefinitionEventConsumer {
    void consume(TechnicalStageDefinitionEvent event);
}
