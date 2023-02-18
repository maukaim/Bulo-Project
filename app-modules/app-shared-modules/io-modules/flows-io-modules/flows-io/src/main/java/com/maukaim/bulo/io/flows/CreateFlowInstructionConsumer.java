package com.maukaim.bulo.io.flows;

import com.maukaim.bulo.io.flows.events.CreateFlowInstruction;

public interface CreateFlowInstructionConsumer {
    String onCreateOrUpdate(CreateFlowInstruction instruction);
}
