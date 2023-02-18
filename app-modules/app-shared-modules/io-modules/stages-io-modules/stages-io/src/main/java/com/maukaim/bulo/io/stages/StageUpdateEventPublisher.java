package com.maukaim.bulo.io.stages;

import com.maukaim.bulo.io.stages.events.StageUpdateEvent;

public interface StageUpdateEventPublisher {
    boolean publish(StageUpdateEvent event);
}
