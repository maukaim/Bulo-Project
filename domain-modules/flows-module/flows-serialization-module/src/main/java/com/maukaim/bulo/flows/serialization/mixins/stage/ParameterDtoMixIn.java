package com.maukaim.bulo.flows.serialization.mixins.stage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParameterDtoMixIn {
    @JsonCreator
    public ParameterDtoMixIn(@JsonProperty("value") String value,
                             @JsonProperty("name") String name) {
    }
}
