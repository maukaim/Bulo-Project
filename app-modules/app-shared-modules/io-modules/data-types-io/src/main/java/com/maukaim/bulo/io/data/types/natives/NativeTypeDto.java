package com.maukaim.bulo.io.data.types.natives;

import com.maukaim.bulo.io.data.types.DataTypeCategoryDto;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;

public abstract class NativeTypeDto implements ParameterTypeDto, IoTypeDto {
    protected final boolean required;

    public NativeTypeDto(boolean required) {
        this.required = required;
    }

    public abstract NativeTypeCategoryDto getNativeTypeCategory();

    public DataTypeCategoryDto getDataTypeCategory() {
        return DataTypeCategoryDto.NATIVE;
    }

    public boolean isRequired() {
        return required;
    }
}
