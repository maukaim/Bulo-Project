package com.maukaim.bulo.io.data.types.natives.impl;

import com.maukaim.bulo.io.data.types.natives.NativeTypeCategoryDto;
import com.maukaim.bulo.io.data.types.natives.NativeTypeDto;

public class BooleanTypeDto extends NativeTypeDto {

    public BooleanTypeDto(boolean required) {
        super(required);
    }

    @Override
    public NativeTypeCategoryDto getNativeTypeCategory() {
        return NativeTypeCategoryDto.BOOLEAN;
    }

    @Override
    public String toString() {
        return "BooleanTypeDto{" +
                "nativeTypeCategory=" + getNativeTypeCategory() +
                ", dataTypeCategory=" + getDataTypeCategory() +
                ", required=" + required +
                '}';
    }
}
