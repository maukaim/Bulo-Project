package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.io.flows.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.events.RemoveFlowInstruction;

public class RemoveFlowInstructionConsumerImpl implements RemoveFlowInstructionConsumer {
    private final FlowService flowService;

    public RemoveFlowInstructionConsumerImpl(FlowService flowService) {
        this.flowService = flowService;
    }

    @Override
    public void onRemove(RemoveFlowInstruction instruction) {
        System.out.println("Consume instruction: " + instruction);
        String flowId = instruction.getFlowId();
        this.flowService.archive(flowId);
    }
}
