package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

public class InputsTSDValidator implements TechnicalStageDefinitionValidator {

    @Override
    public boolean validate(TechnicalStageDefinition definition) {
        return true;
    }
}
