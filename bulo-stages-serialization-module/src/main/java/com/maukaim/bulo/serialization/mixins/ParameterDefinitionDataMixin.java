package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.ParameterDefinitionData;

import java.util.List;

public class ParameterDefinitionDataMixin {
    @JsonCreator
    public ParameterDefinitionDataMixin(@JsonProperty("name") String name,
                                        @JsonProperty("valueType") String valueType,
                                        @JsonProperty("hint") String hint,
                                        @JsonProperty("description") String description,
                                        @JsonProperty("required") boolean required){}
}
