package com.maukaim.bulo.io.flows.client;

import com.maukaim.bulo.io.flows.client.model.FlowDto;

import java.util.List;

public interface GetAllFlowsInstructionConsumer {
    List<FlowDto> getAll();
}
