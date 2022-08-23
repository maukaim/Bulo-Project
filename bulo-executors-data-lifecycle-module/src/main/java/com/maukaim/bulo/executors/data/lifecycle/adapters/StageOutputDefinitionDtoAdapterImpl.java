package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.models.StageOutputDefinition;
import com.maukaim.bulo.executors.io.out.model.StageOutputDefinitionDto;

public class StageOutputDefinitionDtoAdapterImpl implements StageOutputDefinitionDtoAdapter {
    @Override
    public StageOutputDefinitionDto adapte(StageOutputDefinition outputDefinition) {
        return new StageOutputDefinitionDto(
                outputDefinition.canBeMultiple(),
                outputDefinition.getIOTypeId()
        );
    }
}
