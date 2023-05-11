package com.maukaim.bulo.serialization.executors.system.mixins.runs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class StandardStageRunEventMixIn {
    @JsonCreator
    public StandardStageRunEventMixIn(@JsonProperty("stageRunId") String stageRunId,
                                      @JsonProperty("instant") Instant instant) {
    }
}
