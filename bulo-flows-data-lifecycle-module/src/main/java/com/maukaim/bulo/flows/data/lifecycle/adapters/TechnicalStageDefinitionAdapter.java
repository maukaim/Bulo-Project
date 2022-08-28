package com.maukaim.bulo.flows.data.lifecycle.adapters;


import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.io.definition.TechnicalStageDefinitionDto;

public interface TechnicalStageDefinitionAdapter {
    StageDefinition adapte(TechnicalStageDefinitionDto dto);
}
