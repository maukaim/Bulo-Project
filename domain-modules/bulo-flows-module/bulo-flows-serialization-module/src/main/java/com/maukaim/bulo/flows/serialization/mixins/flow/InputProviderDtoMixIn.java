package com.maukaim.bulo.flows.serialization.mixins.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Set;

public class InputProviderDtoMixIn {

    @JsonCreator
    public InputProviderDtoMixIn(@JsonProperty("flowStageId") ContextStageId contextStageId,
                                 @JsonProperty("outputIds") Set<String> outputIds) {
    }
}
