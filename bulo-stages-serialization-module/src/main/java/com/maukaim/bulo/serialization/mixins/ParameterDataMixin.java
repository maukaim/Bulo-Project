package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParameterDataMixin {
    @JsonCreator
    public ParameterDataMixin(@JsonProperty("value") String value,
                              @JsonProperty("name") String name,
                              @JsonProperty("valueType") String valueType,
                              @JsonProperty("additionalDetails") String additionalDetails) {
    }
}
