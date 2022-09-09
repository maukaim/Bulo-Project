package com.maukaim.bulo.stages.io;

import com.maukaim.bulo.stages.io.events.CreateStageEvent;

public interface CreateStageEventConsumer {
    String consume(CreateStageEvent event);
}
