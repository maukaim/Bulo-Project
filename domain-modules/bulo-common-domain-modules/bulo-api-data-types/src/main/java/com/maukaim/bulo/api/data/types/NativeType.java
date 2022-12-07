package com.maukaim.bulo.api.data.types;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;

public enum NativeType implements DataType, ParameterType {
    STRING,
    BOOLEAN,
    NUMBER;

    @Override
    public DataTypeCategory getDataTypeCategory() {
        return DataTypeCategory.NATIVE;
    }
}
