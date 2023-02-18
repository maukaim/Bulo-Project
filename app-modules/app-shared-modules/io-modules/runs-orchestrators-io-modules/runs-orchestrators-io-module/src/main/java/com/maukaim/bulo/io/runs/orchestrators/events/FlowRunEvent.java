package com.maukaim.bulo.io.runs.orchestrators.events;

import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.FlowRunDto;
import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public class FlowRunEvent implements ExternalEvent {
    private FlowRunDto flowRunDto;
    private Instant instant;

    public FlowRunEvent(FlowRunDto flowRunDto, Instant instant) {
        this.flowRunDto = flowRunDto;
        this.instant = instant;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public FlowRunDto getFlowRunDto() {
        return flowRunDto;
    }

    @Override
    public String toString() {
        return "FlowRunEvent{" +
                "flowRunDto=" + flowRunDto +
                ", instant=" + instant +
                '}';
    }
}
