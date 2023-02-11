package com.maukaim.bulo.commons.io.data.types;

import com.maukaim.bulo.api.data.types.DataTypeCategory;

import static com.maukaim.bulo.api.data.types.DataTypeCategory.ARRAY;

public class ArrayParameterTypeDto implements ParameterTypeDto {
    private ParameterTypeDto contentType;
    private Boolean required;

    public ArrayParameterTypeDto(ParameterTypeDto contentType, boolean required) {
        this.required = required;
        this.contentType = contentType;
    }

    public boolean isRequired() {
        return required;
    }

    public DataTypeCategory getDataTypeCategory() {
        return ARRAY;
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
