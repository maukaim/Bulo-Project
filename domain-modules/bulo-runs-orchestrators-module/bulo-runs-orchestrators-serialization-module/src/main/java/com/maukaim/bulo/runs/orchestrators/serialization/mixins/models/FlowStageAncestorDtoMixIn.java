package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Set;

public class FlowStageAncestorDtoMixIn {
    @JsonCreator
    public FlowStageAncestorDtoMixIn(@JsonProperty("flowStageId") FlowStageId flowStageId,
                                     @JsonProperty("outputIds") Set<String> outputIds){}
}
