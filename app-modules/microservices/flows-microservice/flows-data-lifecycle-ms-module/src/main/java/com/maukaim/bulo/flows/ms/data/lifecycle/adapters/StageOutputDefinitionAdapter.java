package com.maukaim.bulo.flows.ms.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition;
import com.maukaim.bulo.io.flows.system.definition.StageOutputDefinitionDto;

public interface StageOutputDefinitionAdapter {
    StageOutputDefinition adapte(StageOutputDefinitionDto dto);
}
