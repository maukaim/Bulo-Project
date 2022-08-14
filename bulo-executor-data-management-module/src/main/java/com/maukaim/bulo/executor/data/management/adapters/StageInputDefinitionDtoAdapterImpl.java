package com.maukaim.bulo.executor.data.management.adapters;

import com.maukaim.bulo.executor.core.api.models.TechnicalStageInputDefinition;
import com.maukaim.bulo.executor.io.out.model.StageInputDefinitionDto;

public class StageInputDefinitionDtoAdapterImpl implements StageInputDefinitionDtoAdapter {
    @Override
    public StageInputDefinitionDto adapte(TechnicalStageInputDefinition inputDefinition) {
        return new StageInputDefinitionDto(
                inputDefinition.canBeMultiple(),
                inputDefinition.isRequired(),
                inputDefinition.getIOTypeId()
        );
    }
}
