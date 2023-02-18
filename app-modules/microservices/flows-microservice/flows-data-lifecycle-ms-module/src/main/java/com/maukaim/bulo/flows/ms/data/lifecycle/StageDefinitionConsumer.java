package com.maukaim.bulo.flows.ms.data.lifecycle;

import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;

public interface StageDefinitionConsumer {
    void consume(StageDefinitionEvent event);
}
