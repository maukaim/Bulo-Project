package com.maukaim.bulo.serialization.data.types.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.data.types.IoTypeDto;

public class ArrayIoTypeDtoMixIn {
   @JsonCreator
   public ArrayIoTypeDtoMixIn(@JsonProperty("contentType") IoTypeDto contentType,
                              @JsonProperty("required") boolean required) {
   }
}
