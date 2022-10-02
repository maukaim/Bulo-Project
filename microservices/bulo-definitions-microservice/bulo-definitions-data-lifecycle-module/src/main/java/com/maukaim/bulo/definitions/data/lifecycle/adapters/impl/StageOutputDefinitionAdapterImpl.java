package com.maukaim.bulo.definitions.data.lifecycle.adapters.impl;

import com.maukaim.bulo.definitions.data.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.StageOutputDefinitionAdapter;
import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;

public class StageOutputDefinitionAdapterImpl implements StageOutputDefinitionAdapter {
    @Override
    public StageOutputDefinition adapte(StageOutputDefinitionDto dto) {
        return new StageOutputDefinition(
                dto.isCanBeMultiple(),
                dto.getTypeId()
        );
    }
}
