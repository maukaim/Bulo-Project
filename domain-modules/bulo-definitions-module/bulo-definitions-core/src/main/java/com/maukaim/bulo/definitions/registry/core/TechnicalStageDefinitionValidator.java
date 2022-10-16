package com.maukaim.bulo.definitions.registry.core;


import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;

public interface TechnicalStageDefinitionValidator {
    boolean validate(TechnicalStageDefinition definition);
}
