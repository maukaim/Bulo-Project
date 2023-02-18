package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;

public interface StageDefinitionDtoAdapter {
    StageDefinitionDto adapte(StageDefinition stageDefinition);
}
