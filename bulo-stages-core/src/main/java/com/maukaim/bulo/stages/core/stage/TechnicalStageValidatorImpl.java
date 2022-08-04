package com.maukaim.bulo.stages.core.stage;

import com.maukaim.bulo.io.stages.ParameterData;
import com.maukaim.bulo.io.stages.TechnicalStageData;
import com.maukaim.bulo.stages.core.TechnicalStageValidator;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TechnicalStageValidatorImpl implements TechnicalStageValidator {
    @Override
    public ValidationReport validate(TechnicalStageData stageData, TechnicalStageDefinition definition) {
        List<String> failReasons = new ArrayList<>();
        try {
            List<ParameterData> parameters = stageData.getParameters();
            List<ParameterDefinition> parameterDefinitions = definition.getParameters();
            if (parameterDefinitions.size() != parameters.size()) {
                failReasons.add(String.format("StageDefinition expected %s parameter(s) but found %s instead.",
                        parameterDefinitions.size(),
                        parameters.size()
                ));
            }

            Map<String, ParameterData> parametersByName = parameters.stream().collect(Collectors.toMap(ParameterData::getName, p -> p));
            for (ParameterDefinition parameterDefinition : parameterDefinitions) {
                failReasons.addAll(validateParameterDefinition(parameterDefinition, parametersByName));
            }
        } catch (Exception unExpected) {
            failReasons.add(String.format("Unexpected error occurred during validation process of this stage, on behalf of definition %s",
                    definition.getId()
            ));
        }
        return failReasons.isEmpty() ? ValidationReport.yes() : ValidationReport.no(failReasons);
    }

    private List<String> validateParameterDefinition(ParameterDefinition parameterDefinition, Map<String, ParameterData> parametersByName) {

        String expectedName = parameterDefinition.getName();
        ParameterData matchedParameter = parametersByName.get(expectedName);
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
