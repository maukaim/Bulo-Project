package com.maukaim.bulo.serialization.data.types.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.data.types.IoTypeDto;

import java.util.Map;

public class BuloIoTypeDtoMixIn {
    @JsonCreator
    protected BuloIoTypeDtoMixIn(@JsonProperty("fields") Map<String, IoTypeDto> fields,
                                 @JsonProperty("required") boolean isRequired) {
    }

}
