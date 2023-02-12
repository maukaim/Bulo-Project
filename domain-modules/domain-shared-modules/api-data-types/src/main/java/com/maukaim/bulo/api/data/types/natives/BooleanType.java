package com.maukaim.bulo.api.data.types.natives;


import com.maukaim.bulo.api.data.types.NativeType;

public class BooleanType extends NativeType {

    public static BooleanType required(){
        return new BooleanType(true);
    }
    public static BooleanType notRequired(){
        return new BooleanType(false);
    }

    public BooleanType(boolean required) {
        super(required);
    }

    @Override
    public NativeTypeCategory getNativeTypeCategory() {
        return NativeTypeCategory.BOOLEAN;
    }

    @Override
    public String toString() {
        return "BooleanType{" +
                "dataTypeCategory=" + getDataTypeCategory() +
                ", nativeTypeCategory=" + getNativeTypeCategory() +
                ", required=" + required +
                '}';
    }
}
