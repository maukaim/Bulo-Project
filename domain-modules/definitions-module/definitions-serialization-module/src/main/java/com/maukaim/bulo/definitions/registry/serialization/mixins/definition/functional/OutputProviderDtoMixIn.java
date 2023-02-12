package com.maukaim.bulo.definitions.registry.serialization.mixins.definition.functional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.instructions.models.functional.IoDependencyDto;
import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Set;

public class OutputProviderDtoMixIn {
    @JsonCreator
    public OutputProviderDtoMixIn(@JsonProperty("contextStageId") ContextStageId contextStageId,
                                  @JsonProperty("outputIds") Set<String> outputIds) {
    }
}
