package com.maukaim.bulo.definitions.data.lifecycle.adapters.impl;

import com.maukaim.bulo.definitions.data.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.StageOutputDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.io.models.StageOutputDefinitionDto;

public class StageOutputDefinitionDtoAdapterImpl implements StageOutputDefinitionDtoAdapter {
    @Override
    public StageOutputDefinitionDto adapte(StageOutputDefinition outputDefinition) {
        return new StageOutputDefinitionDto(
                outputDefinition.isCanBeMultiple(),
                outputDefinition.getTypeId()
        );
    }
}
