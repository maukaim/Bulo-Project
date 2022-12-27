package com.maukaim.bulo.executors.data.models;

import com.maukaim.bulo.api.data.types.DataTypeCategory;
import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.commons.models.definitions.OutputDefinition;

public class StageOutputDefinition implements OutputDefinition {
    private String name;
    private IoType ioType;


    public StageOutputDefinition(String name, IoType ioType) {
        this.name = name;
        this.ioType = ioType;
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
