package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.io.executors.system.StageRunEvent;

public interface StageRunEventPublisher {
    boolean publish(StageRunEvent event);
}
