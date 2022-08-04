package com.maukaim.bulo.stages.models;

import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;

import java.util.List;

public interface TechnicalStageDefinitionStore {
    TechnicalStageDefinition getById(String definitionId);

    TechnicalStageDefinition put(TechnicalStageDefinition technicalStageDefinition);

    TechnicalStageDefinition remove(TechnicalStageDefinition stage);

    List<TechnicalStageDefinition> getAll();
}
