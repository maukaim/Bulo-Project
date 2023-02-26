package com.maukaim.bulo.serialization.data.types.mixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.io.data.types.ArrayParameterTypeDto;
import com.maukaim.bulo.io.data.types.BuloParameterTypeDto;
import com.maukaim.bulo.io.data.types.natives.NativeTypeDto;

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
