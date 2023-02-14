package com.maukaim.bulo.io.data.types;

import com.maukaim.bulo.api.data.types.DataTypeCategory;

public class ArrayIoTypeDto implements IoTypeDto{
    private IoTypeDto contentType;
    private Boolean required;

    public ArrayIoTypeDto(IoTypeDto contentType, boolean required) {
        this.required = required;
        this.contentType = contentType;
    }

    public boolean isRequired() {
        return required;
    }

    public DataTypeCategory getDataTypeCategory() {
        return DataTypeCategory.ARRAY;
    }

    public IoTypeDto getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "ArrayIoTypeDto{" +
                "contentType=" + contentType +
                ", dataTypeCategory=" + getDataTypeCategory() +
                ", required=" + required +
                '}';
    }
}
