package com.maukaim.bulo.executor.data.management.adapters;

import com.maukaim.bulo.executor.core.api.models.StageOutputDefinition;
import com.maukaim.bulo.executor.io.out.model.StageOutputDefinitionDto;

public interface StageOutputDefinitionDtoAdapter {
    StageOutputDefinitionDto adapte(StageOutputDefinition outputDefinition);
}
