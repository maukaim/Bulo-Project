package com.maukaim.bulo.flows.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StageOutputDefinitionDtoMixIn {
    @JsonCreator
    public StageOutputDefinitionDtoMixIn(@JsonProperty("canBeMultiple") Boolean acceptMultiple,
                                         @JsonProperty("typeId") String typeId) {
    }
}
