package com.maukaim.bulo.api.data.types.io;


import com.maukaim.bulo.api.data.types.BuloType;

import java.util.Map;

public class BuloIoType implements BuloType<IoType>, IoType {
    private final Map<String, IoType> fields;
    private final boolean isRequired;

    public static BuloIoType of(Map<String, IoType> fields, boolean isRequired) {
        return new BuloIoType(fields, isRequired);
    }

    public BuloIoType(Map<String, IoType> fields, boolean isRequired) {
        this.fields = Map.copyOf(fields);
        this.isRequired = isRequired;
    }

    @Override
    public Map<String, IoType> getFields() {
        return Map.copyOf(fields);
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    @Override
    public String toString() {
        return "BuloIoType{" +
                "dataTypeCategory=" + getDataTypeCategory() +
                ", fields=" + fields +
                ", isRequired=" + isRequired +
                '}';
    }
}
