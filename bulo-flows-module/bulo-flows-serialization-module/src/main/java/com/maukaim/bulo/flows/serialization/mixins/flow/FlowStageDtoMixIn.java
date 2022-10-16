package com.maukaim.bulo.flows.serialization.mixins.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.flows.io.flow.IoDependencyDto;

import java.util.Set;

public class FlowStageDtoMixIn {

    @JsonCreator
    public FlowStageDtoMixIn(@JsonProperty("flowStageId") FlowStageId flowStageId,
                             @JsonProperty("ioDependencies") Set<IoDependencyDto> ioDependencies) {
    }
}
