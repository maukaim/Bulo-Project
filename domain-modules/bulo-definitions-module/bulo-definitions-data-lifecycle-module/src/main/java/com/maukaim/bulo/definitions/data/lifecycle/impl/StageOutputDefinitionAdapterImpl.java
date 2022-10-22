package com.maukaim.bulo.definitions.data.lifecycle.impl;

import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageOutputDefinitionAdapter;
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
