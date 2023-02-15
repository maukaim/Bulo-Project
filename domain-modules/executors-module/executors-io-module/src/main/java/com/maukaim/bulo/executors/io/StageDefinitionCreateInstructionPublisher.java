package com.maukaim.bulo.executors.io;


import com.maukaim.bulo.io.definitions.shared.instructions.CreateStageDefinitionInstruction;

public interface StageDefinitionCreateInstructionPublisher {
    boolean publish(CreateStageDefinitionInstruction event);
}
