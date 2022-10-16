package com.maukaim.bulo.executors.serialization.mixins.runs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.executors.io.in.model.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class NeedStageRunCancelEventMixIn {
    @JsonCreator
    public NeedStageRunCancelEventMixIn(@JsonProperty("stageRunId") String stageRunId,
                                        @JsonProperty("instant") Instant instant){

    }
}
