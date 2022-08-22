package com.maukaim.bulo.executor.io;

import com.maukaim.bulo.executor.io.out.StageRunEvent;

public interface StageRunEventPublisher {
    boolean publish(StageRunEvent event);
}
