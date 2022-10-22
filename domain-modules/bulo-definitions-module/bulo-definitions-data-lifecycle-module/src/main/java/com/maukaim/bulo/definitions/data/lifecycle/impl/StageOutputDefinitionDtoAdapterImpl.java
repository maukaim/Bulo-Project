package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageOutputDefinitionDtoAdapter;
import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;

public class StageOutputDefinitionDtoAdapterImpl implements StageOutputDefinitionDtoAdapter {
    @Override
    public StageOutputDefinitionDto adapte(StageOutputDefinition outputDefinition) {
        return new StageOutputDefinitionDto(
                outputDefinition.isCanBeMultiple(),
                outputDefinition.getTypeId()
        );
    }
}
