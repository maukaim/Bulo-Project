package com.maukaim.bulo.io.definitions.system;

import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;

public interface CreateStageDefinitionConsumer {
    String consume(CreateStageDefinitionInstruction event);
}
