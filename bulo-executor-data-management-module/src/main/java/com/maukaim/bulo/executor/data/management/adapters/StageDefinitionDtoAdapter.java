package com.maukaim.bulo.executor.data.management.adapters;

import com.maukaim.bulo.executor.core.api.models.StageDefinition;
import com.maukaim.bulo.executor.io.out.model.StageDefinitionDto;

public interface StageDefinitionDtoAdapter {
    StageDefinitionDto adapte(StageDefinition data);
}
