package com.maukaim.bulo.flows.io.events;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.flows.io.flow.FlowDto;

import java.time.Instant;

public class PutFlowInstruction implements ExternalEvent {
    private FlowDto flowDto;
    private Instant instant;

    public PutFlowInstruction(FlowDto flowDto, Instant instant) {
        this.flowDto = flowDto;
        this.instant = instant;
    }

    public FlowDto getFlowDto() {
        return flowDto;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "CreateFlowRequest{" +
                "flowData=" + flowDto +
                '}';
    }
}
