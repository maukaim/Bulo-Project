package com.maukaim.bulo.definitions.io.models;

import com.maukaim.bulo.commons.models.definitions.InputDefinition;

public class StageInputDefinitionDto implements InputDefinition {
    private Boolean canBeMultiple;
    private Boolean required;
    private String typeId;

    public StageInputDefinitionDto(Boolean acceptMultiple,
                                   Boolean required,
                                   String typeId) {
        this.canBeMultiple = acceptMultiple;
        this.required = required;
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
    public Boolean isRequired() {
        return required;
    }

    @Override
    public String toString() {
        return "StageInputDefinitionDto{" +
                "canBeMultiple=" + canBeMultiple +
                ", required=" + required +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
