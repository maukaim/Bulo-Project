package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.ParameterDefinition;
import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;

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
