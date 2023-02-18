package com.maukaim.bulo.io.stages.client;

import com.maukaim.bulo.io.stages.client.DeleteStageInstruction;

public interface DeleteStageEventConsumer {
    String consume(DeleteStageInstruction event);
}
