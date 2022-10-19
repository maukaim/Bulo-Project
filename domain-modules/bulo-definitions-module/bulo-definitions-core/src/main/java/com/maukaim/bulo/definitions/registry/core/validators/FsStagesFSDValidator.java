package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;

public class InputsFSDValidator implements FunctionalStageDefinitionValidator {

    @Override
    public boolean validate(FunctionalStageDefinition definition) {
        //TODO: Should be same as root stages
        return true;
    }
}
