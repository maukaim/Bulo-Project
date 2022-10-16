package com.maukaim.bulo.definitions.io;


import com.maukaim.bulo.commons.io.instructions.TechnicalStageDefinitionCreateInstruction;

public interface TechnicalStageDefinitionCreateInstructionConsumer {
    void consume(TechnicalStageDefinitionCreateInstruction event);
}
