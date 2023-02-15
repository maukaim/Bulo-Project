package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.io.shared.ExternalEvent;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowEventType;

import java.time.Instant;

public class FlowEvent implements ExternalEvent {
    private final Instant instant;
    private final FlowDto flow;
    private final FlowEventType flowEventType;

    public FlowEvent(FlowDto flow,
                     FlowEventType flowEventType,
                     Instant instant) {
        this.flow = flow;
        this.flowEventType = flowEventType;
        this.instant = instant;
    }

    public FlowDto getFlow() {
        return flow;
    }

    public FlowEventType getFlowEventType() {
        return flowEventType;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "FlowEvent{" +
                "instant=" + instant +
                ", flow=" + flow +
                ", flowEventType=" + flowEventType +
                '}';
    }
}
