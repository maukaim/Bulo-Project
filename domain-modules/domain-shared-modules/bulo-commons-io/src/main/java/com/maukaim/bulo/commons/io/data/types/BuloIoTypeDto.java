package com.maukaim.bulo.commons.io.data.types;

import com.maukaim.bulo.api.data.types.DataTypeCategory;

import java.util.Map;

public class BuloIoTypeDto implements IoTypeDto {
    private final Map<String, IoTypeDto> fields;
    private final boolean isRequired;

    public BuloIoTypeDto(Map<String, IoTypeDto> fields, boolean isRequired) {
        this.fields = Map.copyOf(fields);
        this.isRequired = isRequired;
    }

    public Map<String, IoTypeDto> getFields() {
        return Map.copyOf(fields);
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    @Override
    public DataTypeCategory getDataTypeCategory() {
        return DataTypeCategory.BULO;
    }

    @Override
    public String toString() {
        return "BuloIoTypeDto{" +
                "dataTypeCategory=" + getDataTypeCategory() +
                ", fields=" + fields +
                ", isRequired=" + isRequired +
                '}';
    }
}
