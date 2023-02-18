package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.data.lifecycle.FlowAdapter;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.io.flows.CreateFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.events.CreateFlowInstruction;

public class CreateFlowInstructionConsumerImpl implements CreateFlowInstructionConsumer {
    private final FlowService flowService;
    private final FlowAdapter flowAdapter;

    public CreateFlowInstructionConsumerImpl(FlowService flowService, FlowAdapter flowAdapter) {
        this.flowService = flowService;
        this.flowAdapter = flowAdapter;
    }

    @Override
    public String onCreateOrUpdate(CreateFlowInstruction instruction) {
        System.out.println("Consume instruction: " + instruction);
        Flow flowToCreate = this.flowAdapter.adapte(instruction.getFlow());
        return this.flowService.create(flowToCreate);
    }
}
