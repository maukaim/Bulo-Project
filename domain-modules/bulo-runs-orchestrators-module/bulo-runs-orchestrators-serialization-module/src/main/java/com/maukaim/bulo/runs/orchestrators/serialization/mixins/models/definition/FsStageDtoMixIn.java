package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.definition;

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
