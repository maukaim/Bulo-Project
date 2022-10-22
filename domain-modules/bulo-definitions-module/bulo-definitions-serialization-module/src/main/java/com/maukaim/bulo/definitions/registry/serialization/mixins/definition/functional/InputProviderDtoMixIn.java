package com.maukaim.bulo.definitions.registry.serialization.mixins.definition.functional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Set;

public class InputProviderDtoMixIn {
    @JsonCreator
    public InputProviderDtoMixIn(@JsonProperty("fsStageId")  ContextualizedStageId contextualizedStageId,
                                 @JsonProperty("outputIds")  Set<String> outputIds) {
    }
}
