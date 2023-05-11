package com.maukaim.bulo.io.data.types.natives.impl;

import com.maukaim.bulo.io.data.types.natives.NativeTypeCategoryDto;
import com.maukaim.bulo.io.data.types.natives.NativeTypeDto;

public class StringTypeDto extends NativeTypeDto {

    public static StringTypeDto required(){
        return new StringTypeDto(true);
    }

    public static StringTypeDto notRequired(){
        return new StringTypeDto(false);
    }

    public StringTypeDto(boolean required) {
        super(required);
    }

    @Override
    public NativeTypeCategoryDto getNativeTypeCategory() {
        return NativeTypeCategoryDto.STRING;
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
