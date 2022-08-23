package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.models.StageOutputDefinition;
import com.maukaim.bulo.executors.io.out.model.StageOutputDefinitionDto;

public interface StageOutputDefinitionDtoAdapter {
    StageOutputDefinitionDto adapte(StageOutputDefinition outputDefinition);
}
