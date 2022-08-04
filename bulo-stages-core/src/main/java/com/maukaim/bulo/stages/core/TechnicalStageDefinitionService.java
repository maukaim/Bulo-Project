package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.io.definitions.TechnicalStageDefinitionData;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;

import java.util.List;

public interface TechnicalStageDefinitionService {
    TechnicalStageDefinition put(TechnicalStageDefinitionData data);

    TechnicalStageDefinition remove(String definitionId);

    TechnicalStageDefinition getById(String definitionId);

    List<TechnicalStageDefinition> getAll();
}
