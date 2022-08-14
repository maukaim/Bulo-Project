package com.maukaim.bulo.executor.data.management.adapters;

import com.maukaim.bulo.executor.core.api.models.TechnicalStageOutputDefinition;
import com.maukaim.bulo.executor.io.out.model.StageOutputDefinitionDto;

public class StageOutputDefinitionDtoAdapterImpl implements StageOutputDefinitionDtoAdapter {
    @Override
    public StageOutputDefinitionDto adapte(TechnicalStageOutputDefinition outputDefinition) {
        return new StageOutputDefinitionDto(
                outputDefinition.canBeMultiple(),
                outputDefinition.getIOTypeId()
        );
    }
}
