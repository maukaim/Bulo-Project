package com.maukaim.bulo.io.runs.orchestrators.system.events;

import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowRunDto;
import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public class FlowRunEvent implements ExternalEvent {
    private final FlowRunDto flowRunDto;
    private final Instant instant;

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
