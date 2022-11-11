package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageDependencyDto;

import java.util.Set;

public class FlowRunStageDtoMixIn {
    @JsonCreator
    public FlowRunStageDtoMixIn(@JsonProperty("flowStageId") ContextStageId contextStageId,
                                @JsonProperty("flowStageDependencies") Set<FlowStageDependencyDto> flowStageDependencies){}
}
