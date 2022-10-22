package com.maukaim.bulo.definitions.data.definition;

import com.maukaim.bulo.commons.models.definitions.InputDefinition;

public class StageInputDefinition implements InputDefinition {
    private Boolean canBeMultiple;
    private Boolean required;
    private String typeId;

    public StageInputDefinition(Boolean acceptMultiple,
                                Boolean required,
                                String typeId) {
        this.canBeMultiple = acceptMultiple;
        this.required = required;
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
    public Boolean isRequired() {
        return required;
    }
}
