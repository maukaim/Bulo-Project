package com.maukaim.bulo.flows.data.models.definition;

import com.maukaim.bulo.api.data.types.DataTypeCategory;
import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.commons.models.definitions.InputDefinition;

public class StageInputDefinition implements InputDefinition {
    private final IoType ioType;

    public StageInputDefinition(IoType ioType) {
        this.ioType = ioType;
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
    public Boolean isRequired() {
        return ioType.isRequired();
    }

    @Override
    public String toString() {
        return "StageInputDefinition{" +
                ", typeId='" + ioType + '\'' +
                '}';
    }
}
