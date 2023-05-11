package com.maukaim.bulo.definitions.data.definition;

import com.maukaim.bulo.api.data.types.DataTypeCategory;
import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.commons.models.definitions.OutputDefinition;

public class StageOutputDefinition implements OutputDefinition {
    private final IoType ioType;

    public StageOutputDefinition(IoType ioType) {
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

    public boolean isAlwaysPresent(){
        return getType().isRequired();
    }

    @Override
    public String toString() {
        return "StageOutputDefinition{" +
                "ioType=" + ioType +
                '}';
    }
}
