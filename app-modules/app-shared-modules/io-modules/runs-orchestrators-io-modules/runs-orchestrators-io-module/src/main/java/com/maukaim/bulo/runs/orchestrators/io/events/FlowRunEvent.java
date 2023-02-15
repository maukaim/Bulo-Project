package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.io.shared.ExternalEvent;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunDto;

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
