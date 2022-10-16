package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;

import java.util.List;

public interface TechnicalStageDefinitionService {
    TechnicalStageDefinition put(TechnicalStageDefinition definition);

    TechnicalStageDefinition remove(String definitionId);

    TechnicalStageDefinition getById(String definitionId);

    List<TechnicalStageDefinition> getAll();
}
