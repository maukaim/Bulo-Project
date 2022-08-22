package com.maukaim.bulo.executor.data.management.adapters;

import com.maukaim.bulo.executor.core.api.models.StageInputDefinition;
import com.maukaim.bulo.executor.io.out.model.StageInputDefinitionDto;

public class StageInputDefinitionDtoAdapterImpl implements StageInputDefinitionDtoAdapter {
    @Override
    public StageInputDefinitionDto adapte(StageInputDefinition inputDefinition) {
        return new StageInputDefinitionDto(
                inputDefinition.canBeMultiple(),
                inputDefinition.isRequired(),
                inputDefinition.getIOTypeId()
        );
    }
}
