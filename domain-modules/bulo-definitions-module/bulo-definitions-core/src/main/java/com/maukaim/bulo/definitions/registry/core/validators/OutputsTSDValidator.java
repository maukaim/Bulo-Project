package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;

public class OutputsTSDValidator implements TechnicalStageDefinitionValidator {

    @Override
    public boolean validate(TechnicalStageDefinition definition) {
        return true;
    }
}
