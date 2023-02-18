package com.maukaim.bulo.io.flows.system;

import com.maukaim.bulo.io.flows.system.events.CreateFlowInstruction;

public interface CreateFlowInstructionConsumer {
    String onCreateOrUpdate(CreateFlowInstruction instruction);
}
