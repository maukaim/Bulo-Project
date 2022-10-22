package com.maukaim.bulo.definitions.registry.serialization.mixins.definition.functional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.instructions.models.functional.IoDependencyDto;
import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Set;


public class FsStageDtoMixIn {
    @JsonCreator
    public FsStageDtoMixIn(@JsonProperty("fsStageId") ContextualizedStageId contextualizedStageId,
                           @JsonProperty("ioDependencies") Set<IoDependencyDto> ioDependencies) {
    }
}
