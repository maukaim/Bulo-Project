package com.maukaim.bulo.serialization.executors.system.mixins.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.executors.system.dtos.StageRunResultDto;

import java.time.Instant;

public class StageRunResultEventMixIn {
    @JsonCreator
    public StageRunResultEventMixIn(@JsonProperty("stageRunResult") StageRunResultDto stageRunResult,
                                    @JsonProperty("instant")Instant instant) {
    }
}
