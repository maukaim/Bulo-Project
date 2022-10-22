package com.maukaim.bulo.definitions.data.lifecycle;

import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;

public interface StageDefinitionDtoAdapter {
    StageDefinitionDto adapte(StageDefinition stageDefinition);
}
