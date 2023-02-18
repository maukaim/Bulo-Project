package com.maukaim.bulo.flows.ms.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.io.flows.system.definition.StageInputDefinitionDto;

public interface StageInputDefinitionAdapter {
    StageInputDefinition adapte(StageInputDefinitionDto dto);
}
