package com.maukaim.bulo.flows.serialization.mixins.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.flows.io.flow.FlowDto;
import com.maukaim.bulo.flows.io.flow.FlowEventType;

import java.time.Instant;

public class FlowEventMixIn {
    @JsonCreator
    public FlowEventMixIn(@JsonProperty("flow") FlowDto flowDto,
                          @JsonProperty("flowEventType") FlowEventType flowEventType,
                          @JsonProperty("instant") Instant instant){}
}
