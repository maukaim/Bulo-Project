package com.maukaim.bulo.api.data.types.natives;


import com.maukaim.bulo.api.data.types.NativeType;

public class NumberType extends NativeType {
    public static NumberType required() {
        return new NumberType(true);
    }

    public static NumberType notRequired() {
        return new NumberType(false);
    }

    public NumberType(boolean required) {
        super(required);
    }

    @Override
    public NativeTypeCategory getNativeTypeCategory() {
        return NativeTypeCategory.NUMBER;
    }

    @Override
    public String toString() {
        return "NumberType{" +
                "dataTypeCategory=" + getDataTypeCategory() +
                ", nativeTypeCategory=" + getNativeTypeCategory() +
                ", required=" + required +
                '}';
    }
}
