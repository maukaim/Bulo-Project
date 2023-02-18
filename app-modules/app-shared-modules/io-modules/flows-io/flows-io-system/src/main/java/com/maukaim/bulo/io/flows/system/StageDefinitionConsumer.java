package com.maukaim.bulo.io.flows.system;

import com.maukaim.bulo.io.flows.system.events.StageDefinitionEvent;

public interface StageDefinitionConsumer {
    void consume(StageDefinitionEvent event);
}
