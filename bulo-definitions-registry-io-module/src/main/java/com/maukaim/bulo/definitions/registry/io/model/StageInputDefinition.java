package com.maukaim.bulo.definitions.registry.io.model;

import com.maukaim.bulo.commons.models.IStageInputDefinition;

public class StageInputDefinition implements IStageInputDefinition {
    private Boolean canBeMultiple;
    private Boolean required;
    private Class type;

    public StageInputDefinition(Boolean acceptMultiple,
                                Boolean required,
                                Class type) {
        this.canBeMultiple = acceptMultiple;
        this.required = required;
        this.type = type;
    }

    @Override
    public boolean canBeMultiple() {
        return canBeMultiple;
    }

    @Override
    public Class getType() {
        return type;
    }

    @Override
    public Boolean isRequired() {
        return required;
    }
}
