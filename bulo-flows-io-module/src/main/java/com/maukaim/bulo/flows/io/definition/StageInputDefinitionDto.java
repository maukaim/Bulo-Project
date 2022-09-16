package com.maukaim.bulo.flows.io.definition;

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
