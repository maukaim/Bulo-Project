package com.maukaim.bulo.io.flows;

import com.maukaim.bulo.io.flows.events.StageDefinitionEvent;

public interface StageDefinitionConsumer {
    void consume(StageDefinitionEvent event);
}
