package com.maukaim.bulo.api.data.types.parameters;

import com.maukaim.bulo.api.data.types.ArrayType;

public class ArrayParameterType extends ArrayType<ParameterType> implements ParameterType {
    public ArrayParameterType(ParameterType contentType, boolean required) {
        super(contentType, required);
    }
}
