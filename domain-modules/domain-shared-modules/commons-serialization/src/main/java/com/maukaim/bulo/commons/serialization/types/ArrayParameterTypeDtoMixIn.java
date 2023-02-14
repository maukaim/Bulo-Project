package com.maukaim.bulo.commons.serialization.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;

public class ArrayParameterTypeDtoMixIn {
    @JsonCreator
    public ArrayParameterTypeDtoMixIn(@JsonProperty("contentType") ParameterTypeDto contentType,
                                      @JsonProperty("required") boolean required) {
    }
}
