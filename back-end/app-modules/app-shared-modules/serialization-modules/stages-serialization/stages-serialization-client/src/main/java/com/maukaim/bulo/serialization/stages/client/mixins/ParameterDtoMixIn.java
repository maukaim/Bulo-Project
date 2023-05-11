package com.maukaim.bulo.serialization.stages.client.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParameterDtoMixIn {
    @JsonCreator
    public ParameterDtoMixIn(@JsonProperty("value") String value,
                             @JsonProperty("name") String name,
                             @JsonProperty("additionalDetails") String additionalDetails) {
    }
}
