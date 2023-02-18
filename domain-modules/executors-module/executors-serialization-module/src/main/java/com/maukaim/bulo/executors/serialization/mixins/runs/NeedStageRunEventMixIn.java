package com.maukaim.bulo.executors.serialization.mixins.runs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.executors.system.in.model.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class NeedStageRunEventMixIn {
    @JsonCreator
    public NeedStageRunEventMixIn(@JsonProperty("stageId") String stageId,
                                  @JsonProperty("stageRunId") String stageRunId,
                                  @JsonProperty("dependencies") Set<StageRunDependencyDto> dependencies,
                                  @JsonProperty("instant") Instant instant){

    }
}
