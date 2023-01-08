package com.maukaim.bulo.runners.api.models;

import com.maukaim.bulo.api.data.types.DataTypeCategory;
import com.maukaim.bulo.api.data.types.io.IoType;

public class StageInputDefinition {
    private String name;
    private IoType type;

    public StageInputDefinition(String name,
                                IoType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean canBeMultiple() {
        return DataTypeCategory.ARRAY.equals(this.getType().getDataTypeCategory());
    }

    public IoType getType() {
        return type;
    }

    public Boolean isRequired() {
        return type.isRequired();
    }

    @Override
    public String toString() {
        return "StageInputDefinition{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
