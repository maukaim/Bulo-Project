package com.maukaim.bulo.definitions.io;

import com.maukaim.bulo.io.definitions.shared.instructions.CreateStageDefinitionInstruction;

public interface CreateStageDefinitionConsumer {
    String consume(CreateStageDefinitionInstruction event);
}
