package com.maukaim.bulo.flows.io;

import com.maukaim.bulo.flows.io.events.PutFlowInstruction;

public interface CreateFlowInstructionConsumer {
    void onCreateOrUpdate(PutFlowInstruction instruction);
}
