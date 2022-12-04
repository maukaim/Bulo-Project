package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageInputDefinitionDtoAdapter;
import com.maukaim.bulo.executors.data.models.StageInputDefinition;

public class StageInputDefinitionDtoAdapterImpl implements StageInputDefinitionDtoAdapter {
    @Override
    public StageInputDefinitionDto adapte(StageInputDefinition inputDefinition) {
        return new StageInputDefinitionDto(
                inputDefinition.isCanBeMultiple(),
                inputDefinition.isRequired(),
                inputDefinition.getTypeId()
        );
    }
}
