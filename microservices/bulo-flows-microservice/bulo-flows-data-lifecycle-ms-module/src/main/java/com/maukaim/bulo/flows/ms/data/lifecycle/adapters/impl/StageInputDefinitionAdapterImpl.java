package com.maukaim.bulo.flows.ms.data.lifecycle.adapters.impl;

import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageInputDefinitionAdapter;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.io.definition.StageInputDefinitionDto;

public class StageInputDefinitionAdapterImpl implements StageInputDefinitionAdapter {
    @Override
    public StageInputDefinition adapte(StageInputDefinitionDto dto) {
        return new StageInputDefinition(dto.isCanBeMultiple(), dto.isRequired(), dto.getTypeId());
    }
}
