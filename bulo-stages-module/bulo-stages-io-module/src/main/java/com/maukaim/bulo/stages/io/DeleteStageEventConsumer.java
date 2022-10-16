package com.maukaim.bulo.stages.io;

import com.maukaim.bulo.stages.io.events.DeleteStageInstruction;

public interface DeleteStageEventConsumer {
    String consume(DeleteStageInstruction event);
}
