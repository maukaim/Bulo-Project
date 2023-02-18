package com.maukaim.bulo.executors.data.lifecycle;


import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;

public interface StageDefinitionCreateInstructionPublisher {
    boolean publish(CreateStageDefinitionInstruction event);
}
