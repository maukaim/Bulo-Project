package com.maukaim.bulo.io.data.types;


public class ArrayIoTypeDto implements IoTypeDto{
    private final IoTypeDto contentType;
    private final Boolean required;

    public ArrayIoTypeDto(IoTypeDto contentType, boolean required) {
        this.required = required;
        this.contentType = contentType;
    }

    public boolean isRequired() {
        return required;
    }

    public DataTypeCategoryDto getDataTypeCategory() {
        return DataTypeCategoryDto.ARRAY;
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
