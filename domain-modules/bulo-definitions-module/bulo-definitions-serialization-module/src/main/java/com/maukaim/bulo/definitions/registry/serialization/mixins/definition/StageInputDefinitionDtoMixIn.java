package com.maukaim.bulo.definitions.registry.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StageInputDefinitionDtoMixIn {
    @JsonCreator
    public StageInputDefinitionDtoMixIn(
            @JsonProperty("canBeMultiple") Boolean acceptMultiple,
            @JsonProperty("required") Boolean required,
            @JsonProperty("typeId") String typeId) {
    }
}
