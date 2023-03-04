package com.maukaim.bulo.io.data.types;

import com.maukaim.bulo.api.data.types.DataTypeCategory;

public class ArrayParameterTypeDto implements ParameterTypeDto {
    private final ParameterTypeDto contentType;
    private final Boolean required;

    public ArrayParameterTypeDto(ParameterTypeDto contentType, boolean required) {
        this.required = required;
        this.contentType = contentType;
    }

    public boolean isRequired() {
        return required;
    }

    public DataTypeCategory getDataTypeCategory() {
        return DataTypeCategory.ARRAY;
    }

    public ParameterTypeDto getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "ArrayParameterTypeDto{" +
                "contentType=" + contentType +
                ", dataTypeCategory=" + getDataTypeCategory() +
                ", required=" + required +
                '}';
    }
}
