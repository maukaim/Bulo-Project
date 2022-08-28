package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.definition.StageDefinition;

public interface DefinitionService {
    StageDefinition getById(String definitionId);
}
