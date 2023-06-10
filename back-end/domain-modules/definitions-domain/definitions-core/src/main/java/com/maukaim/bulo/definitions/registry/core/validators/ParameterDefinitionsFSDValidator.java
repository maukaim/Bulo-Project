package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;

import java.util.List;

public class ParameterDefinitionsFSDValidator implements FunctionalStageDefinitionValidator {

    @Override
    public boolean validate(FunctionalStageDefinition definition) {
        List<ParameterDefinition> parameters = definition.getParameters();
        if (parameters != null) {
            long count = parameters.stream().map(ParameterDefinition::getName).distinct().count();
            return count == parameters.size();
        }
        return false;
    }
}
