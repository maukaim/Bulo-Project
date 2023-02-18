package com.maukaim.bulo.io.executors.system;


import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;

public interface StageDefinitionCreateInstructionPublisher {
    boolean publish(CreateStageDefinitionInstruction event);
}
