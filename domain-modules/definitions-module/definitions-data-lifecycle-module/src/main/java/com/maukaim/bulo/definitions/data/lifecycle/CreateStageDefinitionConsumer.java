package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;

public interface CreateStageDefinitionConsumer {
    String consume(CreateStageDefinitionInstruction event);
}
