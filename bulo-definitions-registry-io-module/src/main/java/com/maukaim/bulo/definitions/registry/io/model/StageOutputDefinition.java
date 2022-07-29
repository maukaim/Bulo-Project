package com.maukaim.bulo.definitions.registry.io.model;

import com.maukaim.bulo.commons.models.IStageOutputDefinition;

public class StageOutputDefinition implements IStageOutputDefinition {
    private Boolean canBeMultiple;
    private Class type;

    public StageOutputDefinition(Boolean canBeMultiple, Class type) {
        this.canBeMultiple = canBeMultiple;
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
}
