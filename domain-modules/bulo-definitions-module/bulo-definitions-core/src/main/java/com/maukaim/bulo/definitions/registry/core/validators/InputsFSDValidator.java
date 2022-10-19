package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;

public class InputsTSDValidator implements TechnicalStageDefinitionValidator {

    @Override
    public boolean validate(TechnicalStageDefinition definition) {
        return true;
    }
}
