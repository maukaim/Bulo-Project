package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowStageDependencyDto;

import java.util.Set;

public class FlowRunStageDtoMixIn {
    @JsonCreator
    public FlowRunStageDtoMixIn(@JsonProperty("flowStageId") ContextStageId contextStageId,
                                @JsonProperty("flowStageDependencies") Set<FlowStageDependencyDto> flowStageDependencies){}
}
