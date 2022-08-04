package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.stages.ParameterData;

import java.util.List;

public class ParameterDataMixin {
    @JsonCreator
    public ParameterDataMixin(@JsonProperty("value") String value,
                              @JsonProperty("name") String name,
                              @JsonProperty("valueType") String valueType,
                              @JsonProperty("additionalDetails") String additionalDetails){}
}
