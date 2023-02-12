package com.maukaim.bulo.flows.ms.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.io.definition.stageDefinitionDto;

public interface StageDefinitionAdapter {
    StageDefinition adapte(stageDefinitionDto dto);
}
