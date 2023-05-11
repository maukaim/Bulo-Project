package com.maukaim.bulo.stages.ms.data.lifecycle;

import com.maukaim.bulo.io.stages.system.StageUpdateEvent;

public interface StageUpdateEventPublisher {
    boolean publish(StageUpdateEvent event);
}
