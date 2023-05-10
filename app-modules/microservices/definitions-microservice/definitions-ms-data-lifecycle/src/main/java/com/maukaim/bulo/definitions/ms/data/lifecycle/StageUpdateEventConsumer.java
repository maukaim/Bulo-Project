package com.maukaim.bulo.definitions.ms.data.lifecycle;

import com.maukaim.bulo.io.stages.system.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void consume(StageUpdateEvent event);
}
