package com.maukaim.bulo.executors.serialization.mixins.runs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class NeedStageRunCancelEventMixIn {
    @JsonCreator
    public NeedStageRunCancelEventMixIn(@JsonProperty("stageRunId") String stageRunId,
                                        @JsonProperty("instant") Instant instant){

    }
}
