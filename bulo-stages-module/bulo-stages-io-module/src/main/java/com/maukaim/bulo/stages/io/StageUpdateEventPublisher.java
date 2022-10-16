package com.maukaim.bulo.stages.io;

import com.maukaim.bulo.stages.io.events.StageUpdateEvent;

public interface StageUpdateEventPublisher {
    boolean publish(StageUpdateEvent event);
}
