package com.maukaim.bulo.executors.data.models;

import com.maukaim.bulo.api.data.types.DataTypeCategory;
import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.commons.models.definitions.InputDefinition;

public class StageInputDefinition implements InputDefinition {
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

    @Override
    public boolean canBeMultiple() {
        return DataTypeCategory.ARRAY.equals(this.getType().getDataTypeCategory());
    }

    @Override
    public IoType getType() {
        return type;
    }

    @Override
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
