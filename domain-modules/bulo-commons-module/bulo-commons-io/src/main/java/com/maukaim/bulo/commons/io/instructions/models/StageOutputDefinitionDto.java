package com.maukaim.bulo.commons.io.instructions.models;

import com.maukaim.bulo.commons.models.definitions.OutputDefinition;

public class StageOutputDefinitionDto implements OutputDefinition {
    private Boolean canBeMultiple;
    private String typeId;

    public StageOutputDefinitionDto(Boolean canBeMultiple, String typeId) {
        this.canBeMultiple = canBeMultiple;
        this.typeId = typeId;
    }

    @Override
    public boolean isCanBeMultiple() {
        return canBeMultiple;
    }

    @Override
    public String getTypeId() {
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