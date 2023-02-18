package com.maukaim.bulo.io.flows;

import com.maukaim.bulo.io.flows.events.RemoveFlowInstruction;

public interface RemoveFlowInstructionConsumer {
    void onRemove(RemoveFlowInstruction instruction);
}
