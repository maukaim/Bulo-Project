package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.models.StageInputDefinition;
import com.maukaim.bulo.executors.io.out.model.StageInputDefinitionDto;

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
