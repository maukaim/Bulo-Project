package com.maukaim.bulo.executors.io;


import com.maukaim.bulo.commons.io.instructions.TechnicalStageDefinitionCreateInstruction;

public interface StageDefinitionCreateInstructionPublisher {
    boolean publish(TechnicalStageDefinitionCreateInstruction event);
}
