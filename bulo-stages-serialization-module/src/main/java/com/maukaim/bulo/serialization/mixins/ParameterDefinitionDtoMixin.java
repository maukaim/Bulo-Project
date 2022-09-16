package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParameterDefinitionDtoMixin {
    @JsonCreator
    public ParameterDefinitionDtoMixin(@JsonProperty("name") String name,
                                       @JsonProperty("valueType") String valueType,
                                       @JsonProperty("hint") String hint,
                                       @JsonProperty("description") String description,
                                       @JsonProperty("required") boolean required) {
    }
}
