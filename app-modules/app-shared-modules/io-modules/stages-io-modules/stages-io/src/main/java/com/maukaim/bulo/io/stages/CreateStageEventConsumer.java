package com.maukaim.bulo.io.stages;

import com.maukaim.bulo.io.stages.events.CreateStageInstruction;

public interface CreateStageEventConsumer {
    String consume(CreateStageInstruction event);
}
