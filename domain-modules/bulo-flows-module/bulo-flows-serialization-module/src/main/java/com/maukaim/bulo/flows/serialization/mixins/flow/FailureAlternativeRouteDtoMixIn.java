package com.maukaim.bulo.flows.serialization.mixins.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.flows.io.flow.FailureAlternativeRoutesDto;
import com.maukaim.bulo.flows.io.flow.FlowStageDto;
import com.maukaim.bulo.flows.io.flow.OwnerKeyDto;

import java.util.Set;

public class FailureAlternativeRouteDtoMixIn {

    @JsonCreator
    public FailureAlternativeRouteDtoMixIn(@JsonProperty("from") ContextStageId from,
                                           @JsonProperty("to") ContextStageId to,
                                           @JsonProperty("maxUsage") Integer maxUsage) {
    }
}
