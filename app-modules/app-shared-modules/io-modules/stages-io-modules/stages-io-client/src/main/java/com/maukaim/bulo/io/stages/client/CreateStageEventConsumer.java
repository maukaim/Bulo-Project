package com.maukaim.bulo.io.stages.client;

import com.maukaim.bulo.io.stages.client.CreateStageInstruction;

public interface CreateStageEventConsumer {
    String consume(CreateStageInstruction event);
}
