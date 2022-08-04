package com.maukaim.bulo.io;

import com.maukaim.bulo.io.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void consume(StageUpdateEvent event);
}
