package com.maukaim.bulo.api.data.types;

import static com.maukaim.bulo.api.data.types.DataTypeCategory.ARRAY;

public class ArrayType<T> implements DataType{
    private final T contentType;
    private final boolean required;
    public ArrayType(T contentType, boolean required) {
        this.contentType = contentType;
        this.required = required;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public T getContentType() {
        return contentType;
    }

    @Override
    public DataTypeCategory getDataTypeCategory() {
        return ARRAY;
    }
}
