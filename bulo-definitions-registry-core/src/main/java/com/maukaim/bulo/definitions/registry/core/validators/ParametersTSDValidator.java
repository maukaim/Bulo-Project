package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.io.model.ParameterDefinition;
import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

import java.util.List;

public class ParametersTSDValidator implements TechnicalStageDefinitionValidator {
    @Override
    public boolean validate(TechnicalStageDefinition definition) {
        List<ParameterDefinition> parameters = definition.getParameters();
        if (parameters != null) {
            long count = parameters.stream().map(ParameterDefinition::getName).distinct().count();
            return count == parameters.size();
        }
        return false;
    }
}
