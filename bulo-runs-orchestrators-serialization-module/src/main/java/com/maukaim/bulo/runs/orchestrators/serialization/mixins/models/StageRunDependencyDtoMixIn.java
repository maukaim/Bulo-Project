package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunAncestorDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class StageRunDependencyDtoMixIn {
    @JsonCreator
    public StageRunDependencyDtoMixIn(@JsonProperty("stageRunId") String inputId,
                                      @JsonProperty("ancestors") Set<StageRunAncestorDto> ancestors){}
}
