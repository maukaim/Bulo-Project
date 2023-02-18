package com.maukaim.bulo.io.stages;

import com.maukaim.bulo.io.stages.events.DeleteStageInstruction;

public interface DeleteStageEventConsumer {
    String consume(DeleteStageInstruction event);
}
