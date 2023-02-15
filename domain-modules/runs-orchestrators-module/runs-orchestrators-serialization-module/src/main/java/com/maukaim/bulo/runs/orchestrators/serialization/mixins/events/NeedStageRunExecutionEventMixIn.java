package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class NeedStageRunExecutionEventMixIn {
    @JsonCreator
    public NeedStageRunExecutionEventMixIn(@JsonProperty("stageId") String stageId,
                                           @JsonProperty("stageRunId") String stageRunId,
//TODO: What this? Is it useful? Probably not.
                                           @JsonProperty("ioMapping") Map<String, Map<String, Set<String>>> ioMapping,
                                           @JsonProperty("instant") Instant instant) {
    }
}