package com.maukaim.bulo.io.flows.system;

import com.maukaim.bulo.io.flows.system.events.RemoveFlowInstruction;

public interface RemoveFlowInstructionConsumer {
    void onRemove(RemoveFlowInstruction instruction);
}
