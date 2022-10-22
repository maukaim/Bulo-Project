package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.definition.ParameterDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.stage.Parameter;
import com.maukaim.bulo.flows.data.models.stage.Stage;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StageParameterValidatorImpl implements StageParameterValidator {
    //TODO: Ne fait pas sense d'avoir un validateur de parametres ici! Les Stages sont validées par le StageService, et c'est tout !
    @Override
    public void validate(Stage stage, StageDefinition definition) throws FlowValidationException {
        List<Parameter> parameters = stage.getParameters();
        List<ParameterDefinition> parameterDefinitions = definition.getParameters();
        if(this.parametersMissingButExpected(parameters, parameterDefinitions)){
            throw new FlowValidationException("No parameters provided, but some expected. Stage " + stage.getStageId());
        }
        Map<String, Parameter> parameterByNames = parameters.stream().collect(Collectors.toMap(param -> param.getName(), param -> param));
        for (ParameterDefinition parameterDefinition : parameterDefinitions) {
            String name = parameterDefinition.getName();
            if(!parameterByNames.containsKey(name)){
                throw new FlowValidationException(String.format("Parameter %s expected in Stage %s but not provided.", name, stage.getStageId()));
            }
            Parameter parameter = parameterByNames.get(name);
            if(!parameterDefinition.getValueType().equalsIgnoreCase(parameter.getValueType())){
                throw new FlowValidationException(String.format("In stage %s, parameter %s expected type is %s, but provided %s.",
                        stage.getStageId(), name, parameterDefinition.getValueType(), parameter.getValueType()));
            }
        }
    }

    private boolean parametersMissingButExpected(Collection<Parameter> parameters, Collection<ParameterDefinition> parameterDefinitions){
        return (parameters == null || parameters.isEmpty()) &&
                (parameterDefinitions != null && !parameterDefinitions.isEmpty());
    }
}
