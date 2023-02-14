package com.maukaim.bulo.io.data.types.natives;

import com.maukaim.bulo.api.data.types.DataTypeCategory;
import com.maukaim.bulo.api.data.types.natives.NativeTypeCategory;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;

public abstract class NativeTypeDto implements ParameterTypeDto, IoTypeDto {
    protected boolean required;

    public NativeTypeDto(boolean required) {
        this.required = required;
    }

    public abstract NativeTypeCategory getNativeTypeCategory();

    public DataTypeCategory getDataTypeCategory() {
        return DataTypeCategory.NATIVE;
    }

    public boolean isRequired() {
        return required;
    }
}
