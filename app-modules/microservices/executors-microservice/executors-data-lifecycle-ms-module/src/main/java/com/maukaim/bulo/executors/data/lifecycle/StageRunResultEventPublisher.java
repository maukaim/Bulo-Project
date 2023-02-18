package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.io.executors.system.StageRunResultEvent;

public interface StageRunResultEventPublisher {
    boolean publish(StageRunResultEvent event);
}
