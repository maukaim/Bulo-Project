package com.maukaim.bulo.flows.io;

import com.maukaim.bulo.flows.io.events.StageDefinitionEvent;

public interface StageDefinitionConsumer {
    void consume(StageDefinitionEvent event);
}
