package com.maukaim.bulo.flows.io.events;

import com.maukaim.bulo.commons.io.ExternalEvent;

import java.time.Instant;

public class RemoveFlowInstruction implements ExternalEvent {
    private String flowId;
    private Instant instant;

    public RemoveFlowInstruction(String flowId, Instant instant) {
        this.flowId = flowId;
        this.instant =instant;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public String getFlowId() {
        return flowId;
    }
}
