package com.maukaim.bulo.definitions.io;


import com.maukaim.bulo.commons.io.instructions.StageDefinitionCreateInstruction;

public interface TechnicalStageDefinitionCreateInstructionConsumer {
    void consume(StageDefinitionCreateInstruction event);
}
