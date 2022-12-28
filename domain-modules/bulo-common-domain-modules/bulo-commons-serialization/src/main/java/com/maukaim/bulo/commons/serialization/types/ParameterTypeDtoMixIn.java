package com.maukaim.bulo.commons.serialization.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.commons.io.data.types.ArrayParameterTypeDto;
import com.maukaim.bulo.commons.io.data.types.BuloParameterTypeDto;
import com.maukaim.bulo.commons.io.data.types.natives.NativeTypeDto;


@JsonTypeInfo(
        property = "dataTypeCategory",
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BuloParameterTypeDto.class, name = "BULO"),
        @JsonSubTypes.Type(value = NativeTypeDto.class, name = "NATIVE"),
        @JsonSubTypes.Type(value = ArrayParameterTypeDto.class, name = "ARRAY")
})
public interface ParameterTypeDtoMixIn {

}
