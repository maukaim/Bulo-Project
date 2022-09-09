package com.maukaim.bulo.stages.io;

import com.maukaim.bulo.stages.io.events.DeleteStageEvent;

public interface DeleteStageEventConsumer {
    String consume(DeleteStageEvent event);
}
