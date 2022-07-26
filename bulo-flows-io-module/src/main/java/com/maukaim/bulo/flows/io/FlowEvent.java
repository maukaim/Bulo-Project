package com.maukaim.bulo.flows.io;

import com.maukaim.bulo.commons.io.ExternalEvent;

import java.time.Instant;

public class FlowEvent implements ExternalEvent {
    private final Instant instant;
    private final FlowData flowData;
    private final FlowEventType flowEventType;

    public FlowEvent(Instant instant, FlowData flowData, FlowEventType flowEventType) {
        this.instant = instant;
        this.flowData = flowData;
        this.flowEventType = flowEventType;
    }

    @Override
    public Instant getInstant() {
        return null;
    }


    public FlowData getFlowData() {
        return flowData;
    }

    public FlowEventType getFlowEventType() {
        return flowEventType;
    }

    @Override
    public String toString() {
        return "FlowEvent{" +
                "instant=" + instant +
                ", flowData=" + flowData +
                ", flowEventType=" + flowEventType +
                '}';
    }
}
