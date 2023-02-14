package com.maukaim.bulo.commons.serialization.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.io.data.types.ArrayIoTypeDto;
import com.maukaim.bulo.io.data.types.BuloIoTypeDto;
import com.maukaim.bulo.io.data.types.natives.NativeTypeDto;

@JsonTypeInfo(
        property = "dataTypeCategory",
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BuloIoTypeDto.class, name = "BULO"),
        @JsonSubTypes.Type(value = NativeTypeDto.class, name = "NATIVE"),
        @JsonSubTypes.Type(value = ArrayIoTypeDto.class, name = "ARRAY")
})
public class IoTypeDtoMixIn {

}
