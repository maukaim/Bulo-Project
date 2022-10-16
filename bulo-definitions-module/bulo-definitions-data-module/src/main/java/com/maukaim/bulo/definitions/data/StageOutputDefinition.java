package com.maukaim.bulo.definitions.data;

import com.maukaim.bulo.commons.models.definitions.OutputDefinition;

public class StageOutputDefinition implements OutputDefinition {
    private Boolean canBeMultiple;
    private String typeId;

    public StageOutputDefinition(Boolean canBeMultiple, String typeId) {
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
}
