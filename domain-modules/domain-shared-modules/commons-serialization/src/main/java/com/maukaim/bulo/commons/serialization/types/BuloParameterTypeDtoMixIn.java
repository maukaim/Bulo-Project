package com.maukaim.bulo.commons.serialization.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.data.types.IoTypeDto;

import java.util.Map;

public class BuloParameterTypeDtoMixIn {

    @JsonCreator
    protected BuloParameterTypeDtoMixIn(@JsonProperty("fields") Map<String, IoTypeDto> fields,
                                        @JsonProperty("required") boolean isRequired) {
    }

}
