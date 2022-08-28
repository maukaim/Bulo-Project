package com.maukaim.bulo.flows.io.definition;

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
}
