package com.maukaim.bulo.commons.io.data.types;

import com.maukaim.bulo.api.data.types.DataTypeCategory;

import java.util.Map;

public class BuloParameterTypeDto implements ParameterTypeDto {
    private final Map<String, ParameterTypeDto> fields;
    private final boolean isRequired;

    public BuloParameterTypeDto(Map<String, ParameterTypeDto> fields, boolean isRequired) {
        this.fields = Map.copyOf(fields);
        this.isRequired = isRequired;
    }

    public Map<String, ParameterTypeDto> getFields() {
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
        return "BuloParameterTypeDto{" +
                "dataTypeCategory=" + getDataTypeCategory() +
                ", fields=" + fields +
                ", isRequired=" + isRequired +
                '}';
    }
}
