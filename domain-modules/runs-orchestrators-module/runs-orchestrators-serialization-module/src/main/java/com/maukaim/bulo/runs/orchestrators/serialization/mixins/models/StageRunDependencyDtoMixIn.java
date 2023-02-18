package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.StageRunAncestorDto;

import java.util.Set;

public class StageRunDependencyDtoMixIn {
    @JsonCreator
    public StageRunDependencyDtoMixIn(@JsonProperty("stageRunId") String inputId,
                                      @JsonProperty("ancestors") Set<StageRunAncestorDto> ancestors){}
}
