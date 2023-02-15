package com.maukaim.bulo.stages.io;

import com.maukaim.bulo.stages.io.events.TechnicalStageDefinitionEvent;

public interface TechnicalStageDefinitionEventConsumer {
    void consume(TechnicalStageDefinitionEvent event);
}
