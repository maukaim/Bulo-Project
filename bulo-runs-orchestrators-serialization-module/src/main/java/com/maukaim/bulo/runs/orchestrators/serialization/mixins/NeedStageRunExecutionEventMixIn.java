package com.maukaim.bulo.runs.orchestrators.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class NeedStageRunExecutionEventMixIn {
    @JsonCreator
    public NeedStageRunExecutionEventMixIn(@JsonProperty("globalStageId") String globalStageId,
                                           @JsonProperty("stageRunId") String stageRunId,
                                           @JsonProperty("ioMapping") Map<String, Map<String, Set<String>>> ioMapping,
                                           @JsonProperty("instant") Instant instant) {
    }
}
