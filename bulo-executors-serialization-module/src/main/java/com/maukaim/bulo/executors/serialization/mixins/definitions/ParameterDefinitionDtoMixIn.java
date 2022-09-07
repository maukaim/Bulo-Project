package com.maukaim.bulo.executors.serialization.mixins.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.executors.io.out.model.ParameterDefinitionDto;
import com.maukaim.bulo.executors.io.out.model.StageInputDefinitionDto;
import com.maukaim.bulo.executors.io.out.model.StageOutputDefinitionDto;

import java.util.List;
import java.util.Map;

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
