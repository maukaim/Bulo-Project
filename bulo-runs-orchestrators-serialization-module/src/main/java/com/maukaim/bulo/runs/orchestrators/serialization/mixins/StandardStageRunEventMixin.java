package com.maukaim.bulo.runs.orchestrators.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class StandardStageRunEventMixin {
    @JsonCreator
    public StandardStageRunEventMixin(@JsonProperty("stageRunId") String stageRunId,
                                      @JsonProperty("instant") Instant instant) {
    }
}
