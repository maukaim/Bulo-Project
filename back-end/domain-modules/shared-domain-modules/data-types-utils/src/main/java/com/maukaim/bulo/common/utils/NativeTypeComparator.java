package com.maukaim.bulo.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.maukaim.bulo.api.data.types.NativeType;

public class NativeTypeComparator {

    public boolean isValueValid(JsonNode value, NativeType nativeType){
        if (!nativeType.isRequired() && (value == null || value.isNull())){
            return true;
        } else if (value == null || value.isNull()) {
            return false;
        }else{
            return switch (nativeType.getNativeTypeCategory()) {
                case STRING -> value.isTextual();
                case BOOLEAN -> value.isBoolean();
                case NUMBER -> value.isNumber();
            };
        }
    }
}
