package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.FlowRunDto;

import java.time.Instant;

public class FlowRunEventMixIn {
    @JsonCreator
    public FlowRunEventMixIn(@JsonProperty("flowRun") FlowRunDto flowRunDto,
                             @JsonProperty("instant") Instant instant) {
    }
}
