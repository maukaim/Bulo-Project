package com.maukaim.bulo.serialization.flows.system.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.flows.client.model.FlowDto;
import com.maukaim.bulo.io.flows.system.FlowEventType;

import java.time.Instant;

public class FlowEventMixIn {
    @JsonCreator
    public FlowEventMixIn(@JsonProperty("flow") FlowDto flowDto,
                          @JsonProperty("flowEventType") FlowEventType flowEventType,
                          @JsonProperty("instant") Instant instant){}
}
