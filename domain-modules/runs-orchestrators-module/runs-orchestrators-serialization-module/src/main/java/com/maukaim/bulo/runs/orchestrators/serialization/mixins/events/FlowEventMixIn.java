package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowEventType;

import java.time.Instant;

public class FlowEventMixIn {
    @JsonCreator
    public FlowEventMixIn(@JsonProperty("flow") FlowDto flow,
                          @JsonProperty("flowEventType") FlowEventType flowEventType,
                          @JsonProperty("instant") Instant instant) {
    }
}
