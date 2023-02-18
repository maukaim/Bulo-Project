package com.maukaim.bulo.standalone.app.io;

import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstruction;

public class DeleteFlowInstructionConsumerImpl implements RemoveFlowInstructionConsumer {
    private final FlowService flowService;

    public DeleteFlowInstructionConsumerImpl(FlowService flowService) {
        this.flowService = flowService;
    }

    @Override
    public void onRemove(RemoveFlowInstruction instruction) {
        System.out.println("Consume instruction: " + instruction);
        String flowId = instruction.getFlowId();
        this.flowService.archive(flowId);
    }
}
