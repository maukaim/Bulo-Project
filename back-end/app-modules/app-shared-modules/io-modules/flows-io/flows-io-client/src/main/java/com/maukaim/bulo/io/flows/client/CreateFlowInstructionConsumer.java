package com.maukaim.bulo.io.flows.client;

import com.maukaim.bulo.io.flows.client.CreateFlowInstruction;

public interface CreateFlowInstructionConsumer {
    String onCreateOrUpdate(CreateFlowInstruction instruction);
}
