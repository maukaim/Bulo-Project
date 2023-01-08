package com.maukaim.bulo.runners.api.models;

import com.maukaim.bulo.api.data.types.DataTypeCategory;
import com.maukaim.bulo.api.data.types.io.IoType;

public class StageOutputDefinition {
    private String name;
    private IoType ioType;


    public StageOutputDefinition(String name, IoType ioType) {
        this.name = name;
        this.ioType = ioType;
    }

    public String getName() {
        return name;
    }

    public boolean canBeMultiple() {
        return DataTypeCategory.ARRAY.equals(this.getType().getDataTypeCategory());
    }

    public IoType getType() {
        return ioType;
    }

    @Override
    public String toString() {
        return "StageOutputDefinition{" +
                "name='" + name + '\'' +
                ", ioType=" + ioType +
                '}';
    }
}
