package com.maukaim.bulo.io.stages.system;

import com.maukaim.bulo.io.stages.system.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void consume(StageUpdateEvent event);
}
