package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.data.lifecycle.adapters.FlowAdapter;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.io.CreateFlowInstructionConsumer;
import com.maukaim.bulo.flows.io.events.PutFlowInstruction;

public class CreateFlowInstructionConsumerImpl implements CreateFlowInstructionConsumer {
    private final FlowService flowService;
    private final FlowAdapter flowAdapter;

    public CreateFlowInstructionConsumerImpl(FlowService flowService, FlowAdapter flowAdapter) {
        this.flowService = flowService;
        this.flowAdapter = flowAdapter;
    }

    @Override
    public void onCreateOrUpdate(PutFlowInstruction instruction) {
        Flow flowToCreate = this.flowAdapter.adapte(instruction.getFlow());
        this.flowService.put(flowToCreate);
    }
}
