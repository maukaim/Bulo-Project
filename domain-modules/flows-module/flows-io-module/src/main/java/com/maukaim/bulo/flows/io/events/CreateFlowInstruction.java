package com.maukaim.bulo.flows.io.events;

import com.maukaim.bulo.io.shared.ExternalEvent;
import com.maukaim.bulo.flows.io.flow.FlowDto;

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
