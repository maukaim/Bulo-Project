package com.maukaim.bulo.io.stages;

import com.maukaim.bulo.io.stages.events.TechnicalStageDefinitionEvent;

public interface TechnicalStageDefinitionEventConsumer {
    void consume(TechnicalStageDefinitionEvent event);
}
