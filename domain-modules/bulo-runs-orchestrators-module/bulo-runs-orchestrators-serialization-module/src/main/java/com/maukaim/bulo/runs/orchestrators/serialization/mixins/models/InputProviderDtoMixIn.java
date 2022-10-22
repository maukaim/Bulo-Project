package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Set;

public class InputProviderDtoMixIn {
    @JsonCreator
    public InputProviderDtoMixIn(@JsonProperty("flowStageId") ContextualizedStageId contextualizedStageId,
                                 @JsonProperty("outputIds") Set<String> outputIds) {
    }
}
