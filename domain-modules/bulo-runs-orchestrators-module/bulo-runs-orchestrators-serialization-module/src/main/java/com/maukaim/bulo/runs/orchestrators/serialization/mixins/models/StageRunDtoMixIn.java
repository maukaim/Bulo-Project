package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class StageRunDtoMixIn {
    @JsonCreator
    public StageRunDtoMixIn(@JsonProperty("stageRunId") String stageRunId,
                            @JsonProperty("flowStageId") ContextStageId contextStageId,
                            @JsonProperty("flowRunId") String flowRunId,
                            @JsonProperty("stageRunStatus") StageRunStatusDto stageRunStatus,
                            @JsonProperty("executorId") String executorId,
                            @JsonProperty("dependencies") Set<StageRunDependencyDto> dependencies,
                            @JsonProperty("startTime") Instant startTime,
                            @JsonProperty("endTime") Instant endTime){}
}
