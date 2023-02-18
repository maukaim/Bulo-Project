package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.executors.system.out.StageRunEvent;

public interface StageRunEventPublisher {
    boolean publish(StageRunEvent event);
}
