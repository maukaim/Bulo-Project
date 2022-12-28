package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;

import java.util.List;

public class ParameterDefinitionTSDValidator implements TechnicalStageDefinitionValidator {
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
