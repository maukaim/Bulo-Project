package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageAncestorDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageDependencyDto;

import java.util.Set;

public class FlowStageDependencyDtoMixIn {
    @JsonCreator
    public FlowStageDependencyDtoMixIn(@JsonProperty("inputId") String inputId,
                                       @JsonProperty("ancestors") Set<FlowStageAncestorDto> ancestors){}
}
