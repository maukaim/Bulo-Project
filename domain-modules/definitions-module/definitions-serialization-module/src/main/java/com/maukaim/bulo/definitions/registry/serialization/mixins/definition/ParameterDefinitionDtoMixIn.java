package com.maukaim.bulo.definitions.registry.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.data.types.ParameterTypeDto;

public class ParameterDefinitionDtoMixIn {

    @JsonCreator
    public ParameterDefinitionDtoMixIn(
            @JsonProperty("name") String name,
            @JsonProperty("parameterType") ParameterTypeDto parameterType,
            @JsonProperty("hint") String hint,
            @JsonProperty("description") String description) {
    }
}
