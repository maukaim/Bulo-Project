package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParameterDtoMixin {
    @JsonCreator
    public ParameterDtoMixin(@JsonProperty("value") String value,
                             @JsonProperty("name") String name,
                             @JsonProperty("valueType") String valueType,
                             @JsonProperty("additionalDetails") String additionalDetails) {
    }
}
