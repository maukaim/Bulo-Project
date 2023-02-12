package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Set;

public class InputProviderDtoMixIn {
    @JsonCreator
    public InputProviderDtoMixIn(@JsonProperty("fsStageId") ContextStageId contextStageId,
                                 @JsonProperty("outputIds")  Set<String> outputIds) {
    }
}
