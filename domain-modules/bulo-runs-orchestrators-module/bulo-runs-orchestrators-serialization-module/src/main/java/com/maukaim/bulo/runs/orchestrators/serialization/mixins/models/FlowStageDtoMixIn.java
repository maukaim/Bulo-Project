package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.InputDependencyDto;

import java.util.Set;

public class FlowStageDtoMixIn {
    @JsonCreator
    public FlowStageDtoMixIn(@JsonProperty("flowStageId") FlowStageId flowStageId,
                             @JsonProperty("ioDependencies") Set<InputDependencyDto> ioDependencies) {
    }
}
