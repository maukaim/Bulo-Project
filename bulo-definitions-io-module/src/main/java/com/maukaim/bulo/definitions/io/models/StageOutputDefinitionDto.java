package com.maukaim.bulo.definitions.io.models;

import com.maukaim.bulo.commons.models.definitions.OutputDefinition;

public class StageOutputDefinitionDto implements OutputDefinition {
    private Boolean canBeMultiple;
    private String typeId;

    public StageOutputDefinitionDto(Boolean canBeMultiple, String typeId) {
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
        return "StageOutputDefinitionDto{" +
                "canBeMultiple=" + canBeMultiple +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
