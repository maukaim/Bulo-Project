package com.maukaim.bulo.io.stages.system;

import com.maukaim.bulo.io.stages.system.events.StageUpdateEvent;

public interface StageUpdateEventPublisher {
    boolean publish(StageUpdateEvent event);
}
