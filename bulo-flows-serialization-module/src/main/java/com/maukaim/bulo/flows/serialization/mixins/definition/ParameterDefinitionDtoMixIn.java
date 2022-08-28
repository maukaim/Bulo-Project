package com.maukaim.bulo.flows.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParameterDefinitionDtoMixIn {

    @JsonCreator
    public ParameterDefinitionDtoMixIn(
            @JsonProperty("name") String name,
            @JsonProperty("valueType") String valueType,
            @JsonProperty("required") boolean required) {
    }
}
