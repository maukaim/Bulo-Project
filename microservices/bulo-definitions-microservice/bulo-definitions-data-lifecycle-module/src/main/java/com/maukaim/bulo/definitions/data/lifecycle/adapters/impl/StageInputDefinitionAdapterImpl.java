package com.maukaim.bulo.definitions.data.lifecycle.adapters.impl;

import com.maukaim.bulo.definitions.data.StageInputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.StageInputDefinitionAdapter;
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
