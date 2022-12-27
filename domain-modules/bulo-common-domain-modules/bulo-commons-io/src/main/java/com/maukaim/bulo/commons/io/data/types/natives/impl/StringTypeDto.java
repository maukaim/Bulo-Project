package com.maukaim.bulo.commons.io.data.types.natives.impl;

import com.maukaim.bulo.api.data.types.natives.NativeTypeCategory;
import com.maukaim.bulo.commons.io.data.types.natives.NativeTypeDto;

public class StringTypeDto extends NativeTypeDto {

    public StringTypeDto(boolean required) {
        super(required);
    }

    @Override
    public NativeTypeCategory getNativeTypeCategory() {
        return NativeTypeCategory.STRING;
    }

    @Override
    public String toString() {
        return "StringTypeDto{" +
                "nativeTypeCategory=" + getNativeTypeCategory() +
                ", dataTypeCategory=" + getDataTypeCategory() +
                ", required=" + required +
                '}';
    }
}
