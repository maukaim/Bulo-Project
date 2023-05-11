package com.maukaim.bulo.io.data.types;

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
    public DataTypeCategoryDto getDataTypeCategory() {
        return DataTypeCategoryDto.BULO;
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
