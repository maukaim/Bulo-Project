package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flow.InputDependencyDto;

import java.util.Set;

public class FlowStageDtoMixIn {
    @JsonCreator
    public FlowStageDtoMixIn(@JsonProperty("flowStageId") ContextStageId contextStageId,
                             @JsonProperty("ioDependencies") Set<InputDependencyDto> ioDependencies) {
    }
}
