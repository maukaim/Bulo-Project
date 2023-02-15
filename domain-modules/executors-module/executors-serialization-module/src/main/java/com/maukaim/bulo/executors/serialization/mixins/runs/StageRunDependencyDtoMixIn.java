package com.maukaim.bulo.executors.serialization.mixins.runs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.executors.in.model.StageRunAncestorDto;

import java.util.Set;

public class StageRunDependencyDtoMixIn {
    @JsonCreator
    public StageRunDependencyDtoMixIn(@JsonProperty("inputId")String inputId,
                                      @JsonProperty("ancestors") Set<StageRunAncestorDto> ancestors){}
}
