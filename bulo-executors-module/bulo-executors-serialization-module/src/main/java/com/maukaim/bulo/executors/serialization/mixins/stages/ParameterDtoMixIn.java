package com.maukaim.bulo.executors.serialization.mixins.stages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.executors.io.in.model.ParameterDto;

import java.util.List;

public class ParameterDtoMixIn {
    @JsonCreator
    public ParameterDtoMixIn(@JsonProperty("value") String value,
                             @JsonProperty("name") String name,
                             @JsonProperty("valueType") String valueType,
                             @JsonProperty("additionalDetails") String additionalDetails){}
}
