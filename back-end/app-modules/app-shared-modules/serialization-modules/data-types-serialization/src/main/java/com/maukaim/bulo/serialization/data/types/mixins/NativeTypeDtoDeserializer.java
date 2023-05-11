package com.maukaim.bulo.serialization.data.types.mixins;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.maukaim.bulo.io.data.types.natives.NativeTypeCategoryDto;
import com.maukaim.bulo.io.data.types.natives.NativeTypeDto;
import com.maukaim.bulo.io.data.types.natives.impl.BooleanTypeDto;
import com.maukaim.bulo.io.data.types.natives.impl.NumberTypeDto;
import com.maukaim.bulo.io.data.types.natives.impl.StringTypeDto;

import java.io.IOException;

public class NativeTypeDtoDeserializer extends JsonDeserializer<NativeTypeDto> {
    private static final String NATIVE_CATEGORY_FIELD = "nativeTypeCategory";
    private static final String REQUIRED_FIELD = "required";

    @Override
    public NativeTypeDto deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
        JsonNode jsonNode = ctx.readTree(jsonParser);
        JsonNode nativeCategoryField = jsonNode.get(NATIVE_CATEGORY_FIELD);
        if (nativeCategoryField.isTextual()) {
            NativeTypeCategoryDto nativeTypeCategory = NativeTypeCategoryDto.valueOf(nativeCategoryField.asText());
            if (nativeCategoryField == null) {
                throw new IOException("Not recognized value for NativeTypeCategory: " + nativeCategoryField.asText());
            }
            JsonNode requiredField = jsonNode.get(REQUIRED_FIELD);
            return switch (nativeTypeCategory) {
                case STRING -> new StringTypeDto(requiredField.booleanValue());
                case BOOLEAN -> new BooleanTypeDto(requiredField.booleanValue());
                case NUMBER -> new NumberTypeDto(requiredField.booleanValue());
            };
        } else {
            throw new IOException("Not the expected type for NativeTypeCategory field");
        }
    }
}
