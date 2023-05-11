package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class NeedStageRunExecutionEventMixIn {
    @JsonCreator
    public NeedStageRunExecutionEventMixIn(@JsonProperty("stageId") String stageId,
                                           @JsonProperty("stageRunId") String stageRunId,
                                           @JsonProperty("dependencies") Set<StageRunDependencyDto> stageRunDependencies,
                                           @JsonProperty("instant") Instant instant) {
    }
}
