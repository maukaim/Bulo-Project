package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.io.runs.orchestrators.system.models.StageRunStatusDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.RunContextDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class TechnicalStageRunDtoMixIn {
    @JsonCreator
    public TechnicalStageRunDtoMixIn(@JsonProperty("stageRunId") String stageRunId,
                                     @JsonProperty("flowStageId") ContextStageId contextStageId,
                                     @JsonProperty("context") RunContextDto<?> context,
                                     @JsonProperty("status") StageRunStatusDto stageRunStatus,
                                     @JsonProperty("executorId") String executorId,
                                     @JsonProperty("dependencies") Set<StageRunDependencyDto> dependencies,
                                     @JsonProperty("startTime") Instant startTime,
                                     @JsonProperty("endTime") Instant endTime) {
    }
}
