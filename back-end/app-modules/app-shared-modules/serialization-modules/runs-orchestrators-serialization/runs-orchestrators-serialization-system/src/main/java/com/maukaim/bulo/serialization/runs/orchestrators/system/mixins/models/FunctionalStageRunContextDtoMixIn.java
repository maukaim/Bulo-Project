package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunDependencyDto;

import java.util.Set;

public class FunctionalStageRunContextDtoMixIn {
    @JsonCreator
    public FunctionalStageRunContextDtoMixIn(@JsonProperty("contextId") String contextId,
                                             @JsonProperty("stageRunDependencies") Set<StageRunDependencyDto> stageRunDependencies){
    }
}
