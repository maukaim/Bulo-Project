package com.maukaim.bulo.io.flows.system.events;

import com.maukaim.bulo.io.flows.system.flow.FlowDto;
import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public class CreateFlowInstruction implements ExternalEvent {
    private FlowDto flowDto;
    private Instant instant;

    public CreateFlowInstruction(FlowDto flowDto, Instant instant) {
        this.flowDto = flowDto;
        this.instant = instant;
    }

    public FlowDto getFlow() {
        return flowDto;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "CreateFlowRequest{" +
                "flow=" + flowDto +
                '}';
    }
}
