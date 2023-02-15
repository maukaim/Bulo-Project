package com.maukaim.bulo.executors.serialization.mixins.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.executors.out.model.StageRunResultDto;

import java.time.Instant;

public class StageRunResultEventMixIn {
    @JsonCreator
    public StageRunResultEventMixIn(@JsonProperty("stageRunResult") StageRunResultDto stageRunResult,
                                    @JsonProperty("instant")Instant instant) {
    }
}
