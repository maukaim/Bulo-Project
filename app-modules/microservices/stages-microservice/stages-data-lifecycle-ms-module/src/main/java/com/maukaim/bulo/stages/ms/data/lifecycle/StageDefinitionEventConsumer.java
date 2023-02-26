package com.maukaim.bulo.stages.ms.data.lifecycle;

import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;

public interface StageDefinitionEventConsumer {
    void consume(StageDefinitionEvent event);
}
