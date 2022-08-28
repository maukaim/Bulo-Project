package com.maukaim.bulo.flows.data.models.definition;

import com.maukaim.bulo.commons.models.definitions.OutputDefinition;

public class StageOutputDefinition implements OutputDefinition {
    private Boolean canBeMultiple;
    private String typeId;

    public StageOutputDefinition(Boolean canBeMultiple, String typeId) {
        this.canBeMultiple = canBeMultiple;
        this.typeId = typeId;
    }

    @Override
    public boolean canBeMultiple() {
        return canBeMultiple;
    }

    @Override
    public String getIOTypeId() {
        return typeId;
    }

    @Override
    public String toString() {
        return "StageOutputDefinition{" +
                "canBeMultiple=" + canBeMultiple +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
