package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageInputDefinitionDtoAdapter;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;

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
