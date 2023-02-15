package com.maukaim.bulo.io.executors;


import com.maukaim.bulo.io.definitions.shared.instructions.CreateStageDefinitionInstruction;

public interface StageDefinitionCreateInstructionPublisher {
    boolean publish(CreateStageDefinitionInstruction event);
}
