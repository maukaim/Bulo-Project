package com.maukaim.bulo.stages.io;

import com.maukaim.bulo.stages.io.events.CreateStageInstruction;

public interface CreateStageEventConsumer {
    String consume(CreateStageInstruction event);
}
