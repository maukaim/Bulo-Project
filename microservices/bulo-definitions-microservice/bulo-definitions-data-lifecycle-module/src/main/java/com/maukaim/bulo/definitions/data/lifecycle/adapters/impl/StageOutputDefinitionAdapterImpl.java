package com.maukaim.bulo.definitions.data.lifecycle.adapters.impl;

import com.maukaim.bulo.definitions.data.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.StageOutputDefinitionAdapter;
import com.maukaim.bulo.definitions.io.models.StageOutputDefinitionDto;

public class StageOutputDefinitionAdapterImpl implements StageOutputDefinitionAdapter {
    @Override
    public StageOutputDefinition adapte(StageOutputDefinitionDto dto) {
        return new StageOutputDefinition(
                dto.canBeMultiple(),
                dto.getIOTypeId()
        );
    }
}
