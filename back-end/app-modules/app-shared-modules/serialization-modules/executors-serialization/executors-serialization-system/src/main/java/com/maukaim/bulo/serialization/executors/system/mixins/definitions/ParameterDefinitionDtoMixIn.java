package com.maukaim.bulo.serialization.executors.system.mixins.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;

public class ParameterDefinitionDtoMixIn {
    @JsonCreator
    public ParameterDefinitionDtoMixIn(
            @JsonProperty("name") String name,
            @JsonProperty("parameterType") ParameterTypeDto parameterType,
            @JsonProperty("hint") String hint,
            @JsonProperty("description") String description) {
    }
}
