package com.maukaim.bulo.stages.io;

import com.maukaim.bulo.stages.io.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void consume(StageUpdateEvent event);
}
