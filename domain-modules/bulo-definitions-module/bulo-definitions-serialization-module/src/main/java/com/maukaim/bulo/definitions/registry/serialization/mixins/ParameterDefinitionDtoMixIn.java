package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParameterDefinitionDtoMixIn {

    @JsonCreator
    public ParameterDefinitionDtoMixIn(
            @JsonProperty("name") String name,
            @JsonProperty("valueType") String valueType,
            @JsonProperty("hint") String hint,
            @JsonProperty("description") String description,
            @JsonProperty("required") boolean required) {
    }
}
