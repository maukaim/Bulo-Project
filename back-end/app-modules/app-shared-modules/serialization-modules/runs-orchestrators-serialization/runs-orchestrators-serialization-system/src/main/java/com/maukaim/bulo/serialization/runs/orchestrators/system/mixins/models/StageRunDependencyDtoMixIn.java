package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunAncestorDto;

import java.util.Set;

public class StageRunDependencyDtoMixIn {
    @JsonCreator
    public StageRunDependencyDtoMixIn(@JsonProperty("stageRunId") String inputId,
                                      @JsonProperty("ancestors") Set<StageRunAncestorDto> ancestors){}
}
