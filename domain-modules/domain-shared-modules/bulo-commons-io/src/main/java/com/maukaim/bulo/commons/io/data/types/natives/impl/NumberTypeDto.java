package com.maukaim.bulo.commons.io.data.types.natives.impl;

import com.maukaim.bulo.api.data.types.natives.NativeTypeCategory;
import com.maukaim.bulo.commons.io.data.types.natives.NativeTypeDto;

public class NumberTypeDto extends NativeTypeDto {

    public NumberTypeDto(boolean required) {
        super(required);
    }

    @Override
    public NativeTypeCategory getNativeTypeCategory() {
        return NativeTypeCategory.NUMBER;
    }

    @Override
    public String toString() {
        return "NumberTypeDto{" +
                "nativeTypeCategory=" + getNativeTypeCategory() +
                ", dataTypeCategory=" + getDataTypeCategory() +
                ", required=" + required +
                '}';
    }
}
