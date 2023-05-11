package com.maukaim.bulo.io.flows.client;

import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public class RemoveFlowInstruction implements ExternalEvent {
    private final String flowId;
    private final Instant instant;

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
