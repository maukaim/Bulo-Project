package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.io.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.flows.io.events.RemoveFlowInstruction;

public class RemoveFlowInstructionConsumerImpl implements RemoveFlowInstructionConsumer {
    private final FlowService flowService;

    public RemoveFlowInstructionConsumerImpl(FlowService flowService) {
        this.flowService = flowService;
    }

    @Override
    public void onRemove(RemoveFlowInstruction instruction) {
        String flowId = instruction.getFlowId();
        this.flowService.archive(flowId);
    }
}
