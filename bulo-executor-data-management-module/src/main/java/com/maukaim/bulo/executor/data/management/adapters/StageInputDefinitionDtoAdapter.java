package com.maukaim.bulo.executor.data.management.adapters;

import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;
import com.maukaim.bulo.executor.core.api.models.TechnicalStageInputDefinition;
import com.maukaim.bulo.executor.io.out.model.StageInputDefinitionDto;
import com.maukaim.bulo.executor.io.out.model.TechnicalStageDefinitionDto;

public interface StageInputDefinitionDtoAdapter {
    StageInputDefinitionDto adapte(TechnicalStageInputDefinition inputDefinition);
}
