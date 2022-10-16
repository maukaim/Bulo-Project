package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.commons.io.instructions.models.TechnicalStageDefinitionDto;
import com.maukaim.bulo.executors.data.models.StageDefinition;

public interface StageDefinitionDtoAdapter {
    TechnicalStageDefinitionDto adapte(StageDefinition data);
}
