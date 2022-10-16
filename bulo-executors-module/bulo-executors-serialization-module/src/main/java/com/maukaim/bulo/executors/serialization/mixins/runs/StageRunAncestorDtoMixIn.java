package com.maukaim.bulo.executors.serialization.mixins.runs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Set;

public class StageRunAncestorDtoMixIn {
    @JsonCreator
    public StageRunAncestorDtoMixIn(@JsonProperty("stageRunId") String stageRunId,
                                    @JsonProperty("outputIds") Set<String> outputIds){}
}
