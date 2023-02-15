package com.maukaim.bulo.io.executors;

import com.maukaim.bulo.io.executors.out.StageRunEvent;

public interface StageRunEventPublisher {
    boolean publish(StageRunEvent event);
}
