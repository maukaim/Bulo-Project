package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowRunDto;

import java.time.Instant;

public class FlowRunEventMixIn {
    @JsonCreator
    public FlowRunEventMixIn(@JsonProperty("flowRun") FlowRunDto flowRunDto,
                             @JsonProperty("instant") Instant instant) {
    }
}
