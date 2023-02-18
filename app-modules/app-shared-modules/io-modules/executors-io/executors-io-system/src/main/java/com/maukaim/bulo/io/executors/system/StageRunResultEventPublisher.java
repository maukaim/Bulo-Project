package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.executors.system.out.StageRunResultEvent;

public interface StageRunResultEventPublisher {
    boolean publish(StageRunResultEvent event);
}