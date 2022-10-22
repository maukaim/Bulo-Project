package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageInputDefinitionAdapter;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;

public class StageInputDefinitionAdapterImpl implements StageInputDefinitionAdapter {
    @Override
    public StageInputDefinition adapte(StageInputDefinitionDto dto) {
        return new StageInputDefinition(
                dto.isCanBeMultiple(),
                dto.isRequired(),
                dto.getTypeId()
        );
    }
}
