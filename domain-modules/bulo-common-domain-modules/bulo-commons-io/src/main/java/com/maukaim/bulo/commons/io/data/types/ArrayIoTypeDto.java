package com.maukaim.bulo.commons.io.data.types;

import com.maukaim.bulo.api.data.types.DataTypeCategory;

import static com.maukaim.bulo.api.data.types.DataTypeCategory.ARRAY;

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
        return ARRAY;
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
