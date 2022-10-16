package com.maukaim.bulo.flows.io;

import com.maukaim.bulo.flows.io.events.RemoveFlowInstruction;

public interface RemoveFlowInstructionConsumer {
    void onRemove(RemoveFlowInstruction instruction);
}
