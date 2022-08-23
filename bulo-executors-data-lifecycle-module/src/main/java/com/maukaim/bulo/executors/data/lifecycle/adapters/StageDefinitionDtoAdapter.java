package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.models.StageDefinition;
import com.maukaim.bulo.executors.io.out.model.StageDefinitionDto;

public interface StageDefinitionDtoAdapter {
    StageDefinitionDto adapte(StageDefinition data);
}
