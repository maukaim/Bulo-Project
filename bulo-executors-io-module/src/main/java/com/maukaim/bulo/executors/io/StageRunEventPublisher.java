package com.maukaim.bulo.executors.io;

import com.maukaim.bulo.executors.io.out.StageRunEvent;

public interface StageRunEventPublisher {
    boolean publish(StageRunEvent event);
}
