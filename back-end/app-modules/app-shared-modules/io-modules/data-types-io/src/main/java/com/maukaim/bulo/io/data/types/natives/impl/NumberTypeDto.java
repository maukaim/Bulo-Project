package com.maukaim.bulo.io.data.types.natives.impl;

import com.maukaim.bulo.io.data.types.natives.NativeTypeCategoryDto;
import com.maukaim.bulo.io.data.types.natives.NativeTypeDto;

public class NumberTypeDto extends NativeTypeDto {

    public NumberTypeDto(boolean required) {
        super(required);
    }

    @Override
    public NativeTypeCategoryDto getNativeTypeCategory() {
        return NativeTypeCategoryDto.NUMBER;
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
