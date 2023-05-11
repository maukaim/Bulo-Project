package com.maukaim.bulo.serialization.definitions.client.mixins.functional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Set;

public class OutputProviderDtoMixIn {
    @JsonCreator
    public OutputProviderDtoMixIn(@JsonProperty("contextStageId") ContextStageId contextStageId,
                                  @JsonProperty("outputIds") Set<String> outputIds) {
    }
}
