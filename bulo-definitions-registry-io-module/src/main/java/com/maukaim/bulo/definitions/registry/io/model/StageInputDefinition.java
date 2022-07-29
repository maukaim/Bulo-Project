package com.maukaim.bulo.definitions.registry.io.model;

import com.maukaim.bulo.commons.models.IStageInputDefinition;

public class StageInputDefinition implements IStageInputDefinition {
    private Boolean acceptMultiple;
    private Boolean isRequired;
    private Class type;

    public StageInputDefinition(Boolean acceptMultiple, Boolean isRequired, Class type) {
        this.acceptMultiple = acceptMultiple;
        this.isRequired = isRequired;
        this.type = type;
    }

    @Override
    public boolean canBeMultiple() {
        return acceptMultiple;
    }

    @Override
    public Class getType() {
        return type;
    }

    @Override
    public Boolean isRequired() {
        return isRequired;
    }

}
