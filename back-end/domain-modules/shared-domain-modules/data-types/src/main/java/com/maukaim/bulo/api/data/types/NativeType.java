package com.maukaim.bulo.api.data.types;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.api.data.types.natives.NativeTypeCategory;
import com.maukaim.bulo.api.data.types.parameters.ParameterType;

public abstract class NativeType implements DataType, ParameterType, IoType {
    protected final boolean required;

    public NativeType(boolean required) {
        this.required = required;
    }

    public abstract NativeTypeCategory getNativeTypeCategory();

    public DataTypeCategory getDataTypeCategory() {
        return DataTypeCategory.NATIVE;
    }

    @Override
    public boolean isRequired() {
        return required;
    }
}
