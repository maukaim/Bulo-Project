package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunAncestorDto;

import java.util.Set;

public class StageRunAncestorDtoMixIn {
    @JsonCreator
    public StageRunAncestorDtoMixIn(@JsonProperty("stageRunId") String stageRunId,
                                    @JsonProperty("outputIds") Set<String> outputIds){}
}
