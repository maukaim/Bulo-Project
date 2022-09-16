package com.maukaim.bulo.definitions.data.lifecycle.adapters.impl;

import com.maukaim.bulo.definitions.data.StageInputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.StageInputDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.io.models.StageInputDefinitionDto;

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
