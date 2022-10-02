package com.maukaim.bulo.stages.core.validators;

import com.maukaim.bulo.stages.core.TechnicalStageValidator;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;
import com.maukaim.bulo.stages.models.stage.Parameter;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.maukaim.bulo.stages.core.validators.ValidationReport.DEFAULT_SUCCESS_REPORT;


public class TechnicalStageValidatorImpl implements TechnicalStageValidator {
    @Override
    public ValidationReport validate(TechnicalStage actualStage, TechnicalStageDefinition definition) {
        List<String> failReasons = new ArrayList<>();
        try {
            List<Parameter> parameters = actualStage.getParameters();
            List<ParameterDefinition> parameterDefinitions = definition.getParameters();
            if (parameterDefinitions.size() != parameters.size()) {
                failReasons.add(String.format("StageDefinition expected %s parameter(s) but found %s instead.",
                        parameterDefinitions.size(),
                        parameters.size()
                ));
            }

            Map<String, Parameter> parametersByName = parameters.stream().collect(Collectors.toMap(Parameter::getName, p -> p));
            for (ParameterDefinition parameterDefinition : parameterDefinitions) {
                failReasons.addAll(validateParameterDefinition(parameterDefinition, parametersByName));
            }
        } catch (Exception unExpected) {
            failReasons.add(String.format("Unexpected error occurred during validation process of this stage, on behalf of definition %s",
                    definition.getId()
            ));
        }
        return failReasons.isEmpty() ? DEFAULT_SUCCESS_REPORT : ValidationReport.no(failReasons);
    }

    private List<String> validateParameterDefinition(ParameterDefinition parameterDefinition, Map<String, Parameter> parametersByName) {

        String expectedName = parameterDefinition.getName();
        Parameter matchedParameter = parametersByName.get(expectedName);
        if (matchedParameter == null) {
            return List.of(String.format("Expected a parameter named %s but none provided.", expectedName));
        }

        List<String> failReasons = new ArrayList<>();

        if (!parameterDefinition.getValueType().equals(matchedParameter.getValueType())) {
            failReasons.add(String.format("Specified type for parameter %s should be %s but stage specified the following type: %s",
                    expectedName,
                    parameterDefinition.getValueType(),
                    matchedParameter.getValueType()
            ));
        }

        if (matchedParameter.getAdditionalDetails() == null) {
            failReasons.add(String.format("Technical issue. additionalDetails for parameter %s is null, should at least be empty list.",
                    expectedName));
        }

        if (matchedParameter.getValue() == null) {
            failReasons.add(String.format("Value of parameter %s is null, should be at least an empty string.",
                    expectedName));
        }

        return failReasons;
    }

}
