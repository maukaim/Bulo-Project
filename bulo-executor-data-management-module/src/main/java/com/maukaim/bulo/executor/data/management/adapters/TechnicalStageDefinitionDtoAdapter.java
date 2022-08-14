package com.maukaim.bulo.executor.data.management.adapters;

import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;
import com.maukaim.bulo.executor.io.out.model.TechnicalStageDefinitionDto;

public interface TechnicalStageDefinitionDtoAdapter {
    TechnicalStageDefinitionDto adapte(TechnicalStageDefinition data);
}
