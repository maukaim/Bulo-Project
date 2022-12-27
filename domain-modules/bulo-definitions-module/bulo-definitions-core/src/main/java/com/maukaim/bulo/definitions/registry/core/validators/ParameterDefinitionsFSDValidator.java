package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.definitions.data.definition.ParameterDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;

import java.util.List;

public class ParameterDefinitionsFSDValidator implements FunctionalStageDefinitionValidator {
    //TODO: Quand ContextParameter sera en place, valider les Parameters against le type des sub stqges qui
    // on declaré une ContextParameterValue
    @Override
    public boolean isValid(FunctionalStageDefinition definition) {
        List<ParameterDefinition> parameters = definition.getParameters();
        if (parameters != null) {
            long count = parameters.stream().map(ParameterDefinition::getName).distinct().count();
            return count == parameters.size();
        }
        return false;
    }
}
