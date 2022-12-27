package com.maukaim.bulo.commons.io.data.types.natives.impl;

import com.maukaim.bulo.api.data.types.natives.NativeTypeCategory;
import com.maukaim.bulo.commons.io.data.types.natives.NativeTypeDto;

public class BooleanTypeDto extends NativeTypeDto {

    public BooleanTypeDto(boolean required) {
        super(required);
    }

    @Override
    public NativeTypeCategory getNativeTypeCategory() {
        return NativeTypeCategory.BOOLEAN;
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
