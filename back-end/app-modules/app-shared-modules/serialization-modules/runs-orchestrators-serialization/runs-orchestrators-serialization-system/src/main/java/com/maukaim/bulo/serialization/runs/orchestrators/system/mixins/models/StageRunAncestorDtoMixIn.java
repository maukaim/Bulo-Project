package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class StageRunAncestorDtoMixIn {
    @JsonCreator
    public StageRunAncestorDtoMixIn(@JsonProperty("stageRunId") String stageRunId,
                                    @JsonProperty("outputIds") Set<String> outputIds){}
}
