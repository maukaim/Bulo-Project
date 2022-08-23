package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

public interface TechnicalStageDefinitionValidator {
    boolean validate(TechnicalStageDefinition definition);
}
