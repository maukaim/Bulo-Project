package com.maukaim.bulo.api.data.types.parameters;


import com.maukaim.bulo.api.data.types.BuloType;

import java.util.Map;

public class BuloParameterType implements BuloType<ParameterType>, ParameterType {
    private final Map<String, ParameterType> fields;
    private final boolean isRequired;

    public static BuloParameterType of(Map<String, ParameterType> fields, boolean isRequired) {
        return new BuloParameterType(fields, isRequired);
    }

    public BuloParameterType(Map<String, ParameterType> fields, boolean isRequired) {
        this.fields = Map.copyOf(fields);
        this.isRequired = isRequired;
    }

    @Override
    public Map<String, ParameterType> getFields() {
        return Map.copyOf(fields);
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    @Override
    public String toString() {
        return "BuloParameterType{" +
                "dataTypeCategory=" + getDataTypeCategory() +
                ", fields=" + fields +
                ", isRequired=" + isRequired +
                '}';
    }
}
