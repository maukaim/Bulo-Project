package com.maukaim.bulo.definitions.registry.core;


import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;

public interface FunctionalStageDefinitionValidator {
    boolean isValid(FunctionalStageDefinition definition);
}
