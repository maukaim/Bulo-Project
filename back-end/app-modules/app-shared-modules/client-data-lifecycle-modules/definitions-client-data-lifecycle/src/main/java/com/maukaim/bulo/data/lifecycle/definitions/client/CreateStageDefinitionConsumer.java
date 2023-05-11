package com.maukaim.bulo.data.lifecycle.definitions.client;

import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;

public interface CreateStageDefinitionConsumer {
    String consume(CreateStageDefinitionInstruction event);
}
