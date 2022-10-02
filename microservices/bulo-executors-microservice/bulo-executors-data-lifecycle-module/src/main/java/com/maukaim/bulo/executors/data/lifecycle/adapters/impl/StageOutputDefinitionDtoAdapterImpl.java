package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageOutputDefinitionDtoAdapter;
import com.maukaim.bulo.executors.data.models.StageOutputDefinition;

public class StageOutputDefinitionDtoAdapterImpl implements StageOutputDefinitionDtoAdapter {
    @Override
    public StageOutputDefinitionDto adapte(StageOutputDefinition outputDefinition) {
        return new StageOutputDefinitionDto(
                outputDefinition.isCanBeMultiple(),
                outputDefinition.getTypeId()
        );
    }
}
