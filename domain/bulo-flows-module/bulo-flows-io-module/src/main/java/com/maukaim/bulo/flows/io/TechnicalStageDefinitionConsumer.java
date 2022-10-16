package com.maukaim.bulo.flows.io;

import com.maukaim.bulo.flows.io.events.TechnicalStageDefinitionEvent;

public interface TechnicalStageDefinitionConsumer {
    void consume(TechnicalStageDefinitionEvent event);
}
