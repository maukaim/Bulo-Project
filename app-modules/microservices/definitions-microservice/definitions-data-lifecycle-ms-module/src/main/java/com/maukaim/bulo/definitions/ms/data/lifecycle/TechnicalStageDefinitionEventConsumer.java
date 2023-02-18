package com.maukaim.bulo.definitions.ms.data.lifecycle;

import com.maukaim.bulo.io.definitions.system.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;

public interface TechnicalStageDefinitionEventConsumer {
    void consume(StageDefinitionEvent event);

    void consume(ExecutorUpdateEvent event);
}
