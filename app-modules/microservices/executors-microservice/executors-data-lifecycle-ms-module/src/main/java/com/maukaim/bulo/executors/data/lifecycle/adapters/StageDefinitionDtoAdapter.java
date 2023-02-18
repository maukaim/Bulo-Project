package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.io.definitions.client.dtos.technical.TechnicalStageDefinitionDto;
import com.maukaim.bulo.runners.api.models.StageDefinition;

public interface StageDefinitionDtoAdapter {
    TechnicalStageDefinitionDto adapte(StageDefinition data);
}
