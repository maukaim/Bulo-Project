package com.maukaim.bulo.executors.io.out.model;

import com.maukaim.bulo.commons.models.definitions.OutputDefinition;

public class StageOutputDefinitionDto implements OutputDefinition {
    private Boolean acceptMultiple;
    private String typeId;

    public StageOutputDefinitionDto(Boolean acceptMultiple, String typeId) {
        this.acceptMultiple = acceptMultiple;
        this.typeId = typeId;
    }

    @Override
    public boolean isCanBeMultiple() {
        return acceptMultiple;
    }

    @Override
    public String getTypeId() {
        return typeId;
    }

    @Override
    public String toString() {
        return "StageOutputDefinitionDto{" +
                "canBeMultiple=" + acceptMultiple +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
