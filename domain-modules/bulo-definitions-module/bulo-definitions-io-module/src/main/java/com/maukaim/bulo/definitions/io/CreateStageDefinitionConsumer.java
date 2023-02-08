package com.maukaim.bulo.definitions.io;


import com.maukaim.bulo.commons.io.instructions.CreateStageDefinitionInstruction;

public interface CreateStageDefinitionConsumer {
    String consume(CreateStageDefinitionInstruction event);
}
