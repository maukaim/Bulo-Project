package com.maukaim.bulo.flows.serialization.mixins.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.flows.system.flow.FlowDto;
import com.maukaim.bulo.io.flows.system.flow.FlowEventType;

import java.time.Instant;

public class FlowEventMixIn {
    @JsonCreator
    public FlowEventMixIn(@JsonProperty("flow") FlowDto flowDto,
                          @JsonProperty("flowEventType") FlowEventType flowEventType,
                          @JsonProperty("instant") Instant instant){}
}
