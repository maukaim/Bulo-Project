package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.data.lifecycle.flows.client.FlowDtoAdapter;
import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.io.flows.client.GetAllFlowsInstructionConsumer;
import com.maukaim.bulo.io.flows.client.model.FlowDto;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllFlowsInstructionConsumerImpl implements GetAllFlowsInstructionConsumer {
    private final FlowService flowService;
    private final FlowDtoAdapter flowDtoAdapter;

    public GetAllFlowsInstructionConsumerImpl(FlowService flowService,
                                              FlowDtoAdapter flowDtoAdapter) {
        this.flowService = flowService;
        this.flowDtoAdapter = flowDtoAdapter;
    }

    @Override
    public List<FlowDto> getAll() {
        List<Flow> allFlows = this.flowService.getAll();
        return allFlows.stream()
                .map(flowDtoAdapter::adapte)
                .collect(Collectors.toList());
    }
}
