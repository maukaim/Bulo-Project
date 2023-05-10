package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.io.runs.orchestrators.system.models.FunctionalStageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.TechnicalStageRunDto;

public class FlowRunContextDtoMixIn {
    @JsonCreator
    public FlowRunContextDtoMixIn(@JsonProperty("contextId") String contextId){
    }
}
