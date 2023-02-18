package com.maukaim.bulo.flows.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.io.flows.client.model.IoDependencyDto;

import java.util.Set;

public class FlowStageDtoMixIn {

    @JsonCreator
    public FlowStageDtoMixIn(@JsonProperty("flowStageId") ContextStageId contextStageId,
                             @JsonProperty("ioDependencies") Set<IoDependencyDto> ioDependencies) {
    }
}
