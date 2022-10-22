package com.maukaim.bulo.executors.io;


import com.maukaim.bulo.commons.io.instructions.StageDefinitionCreateInstruction;

public interface StageDefinitionCreateInstructionPublisher {
    boolean publish(StageDefinitionCreateInstruction event);
}
