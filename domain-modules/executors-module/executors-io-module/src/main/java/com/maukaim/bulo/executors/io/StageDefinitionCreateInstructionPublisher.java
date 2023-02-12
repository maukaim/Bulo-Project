package com.maukaim.bulo.executors.io;


import com.maukaim.bulo.commons.io.instructions.CreateStageDefinitionInstruction;

public interface StageDefinitionCreateInstructionPublisher {
    boolean publish(CreateStageDefinitionInstruction event);
}
