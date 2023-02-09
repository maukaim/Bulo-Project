package com.maukaim.bulo.flows.io;

import com.maukaim.bulo.flows.io.events.CreateFlowInstruction;

public interface CreateFlowInstructionConsumer {
    String onCreateOrUpdate(CreateFlowInstruction instruction);
}
