package com.maukaim.bulo.definitions.registry.core;


import com.maukaim.bulo.definitions.data.technical.TechnicalStageDefinition;

public interface TechnicalStageDefinitionValidator {
    boolean validate(TechnicalStageDefinition definition);
}
