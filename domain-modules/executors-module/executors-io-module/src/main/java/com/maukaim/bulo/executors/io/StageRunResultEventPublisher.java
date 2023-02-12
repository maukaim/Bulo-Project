package com.maukaim.bulo.executors.io;

import com.maukaim.bulo.executors.io.out.StageRunResultEvent;

public interface StageRunResultEventPublisher {
    boolean publish(StageRunResultEvent event);
}
