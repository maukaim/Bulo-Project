package com.maukaim.bulo.io.data.types;

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

    public DataTypeCategoryDto getDataTypeCategory() {
        return DataTypeCategoryDto.ARRAY;
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
