package com.maukaim.bulo.io.flows.client;

import com.maukaim.bulo.io.flows.client.RemoveFlowInstruction;

public interface RemoveFlowInstructionConsumer {
    void onRemove(RemoveFlowInstruction instruction);
}
