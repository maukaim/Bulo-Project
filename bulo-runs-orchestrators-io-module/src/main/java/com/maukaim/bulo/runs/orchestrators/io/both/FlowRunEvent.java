package com.maukaim.bulo.runs.orchestrators.io.both;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.runs.orchestrators.io.both.model.FlowRunDto;

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
}
