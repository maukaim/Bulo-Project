package com.maukaim.bulo.api.data.types.natives;

import com.maukaim.bulo.api.data.types.NativeType;

public class StringType extends NativeType {

    public static StringType required() {
        return new StringType(true);
    }

    public static StringType notRequired() {
        return new StringType(false);
    }

    public StringType(boolean required) {
        super(required);
    }

    @Override
    public NativeTypeCategory getNativeTypeCategory() {
        return NativeTypeCategory.STRING;
    }

    @Override
    public String toString() {
        return "StringType{" +
                "dataTypeCategory=" + getDataTypeCategory() +
                ", nativeTypeCategory=" + getNativeTypeCategory() +
                ", required=" + required +
                '}';
    }
}
