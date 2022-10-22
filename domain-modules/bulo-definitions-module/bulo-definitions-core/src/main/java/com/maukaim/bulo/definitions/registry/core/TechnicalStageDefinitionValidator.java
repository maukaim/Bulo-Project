package com.maukaim.bulo.definitions.registry.core;


import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;

public interface TechnicalStageDefinitionValidator {
    boolean validate(TechnicalStageDefinition definition);
}
