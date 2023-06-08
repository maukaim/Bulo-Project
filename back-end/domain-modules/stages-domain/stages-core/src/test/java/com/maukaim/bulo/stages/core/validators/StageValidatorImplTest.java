package com.maukaim.bulo.stages.core.validators;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.common.utils.ParameterTypeComparator;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.models.stage.Parameter;
import com.maukaim.bulo.stages.models.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StageValidatorImplTest {
    private StageValidatorImpl stageValidator;
    private final Stage actualStage = mock(Stage.class);
    private final StageDefinition definition = mock(StageDefinition.class);
    private final ParameterType parameterType = mock(ParameterType.class);
    private final ParameterTypeComparator parameterTypeComparator = mock(ParameterTypeComparator.class);
    private final Parameter param = mock(Parameter.class);
    private final ParameterDefinition paramDef = mock(ParameterDefinition.class);
    private final String additionalDetails = "details";
    private final String paramName1 = "param1";
    private final String paramValue1 = "value1";

    @BeforeEach
    public void setup() {
        stageValidator = new StageValidatorImpl(parameterTypeComparator);
        when(parameterTypeComparator.isValueValid(any(), any())).thenReturn(true);

        when(param.getName()).thenReturn(paramName1);
        when(param.getValue()).thenReturn(paramValue1);
        when(param.getAdditionalDetails()).thenReturn(additionalDetails);
        when(paramDef.getName()).thenReturn(paramName1);
        when(paramDef.getParameterType()).thenReturn(parameterType);

        when(actualStage.getParameters()).thenReturn(Collections.singletonList(param));
        when(definition.getParameters()).thenReturn(Collections.singletonList(paramDef));
    }

    @Test
    public void validate_whenAllSuccess_DEFAULT_SUCCESS_REPORTReturned() {
        ValidationReport report = stageValidator.validate(actualStage, definition);
        assertEquals(ValidationReport.DEFAULT_SUCCESS_REPORT, report);
    }

    @Test
    public void validate_whenActualStageParametersIsNull_expectedFailReasonReturned() {
        when(actualStage.getParameters()).thenReturn(null);
        when(definition.getParameters()).thenReturn(Collections.singletonList(mock(ParameterDefinition.class)));

        ValidationReport report = stageValidator.validate(actualStage, definition);

        assertEquals(1, report.getDetails().size());
        assertEquals("Unexpected error occurred during validation process of this stage, on behalf of definition null", report.getDetails().get(0));
    }

    @Test
    public void validate_whenDefinitionParametersIsNull_expectedFailReasonReturned() {
        when(definition.getParameters()).thenReturn(null);

        ValidationReport report = stageValidator.validate(actualStage, definition);

        assertEquals("Unexpected error occurred during validation process of this stage, on behalf of definition null", report.getDetails().get(0));
    }


    @Test
    public void validate_whenActualStageParametersIsEmptyAndDefinitionParametersIsNotEmpty_expectedFailReasonReturned() {
        when(actualStage.getParameters()).thenReturn(new ArrayList<>());
        when(definition.getParameters()).thenReturn(Collections.singletonList(mock(ParameterDefinition.class)));

        ValidationReport report = stageValidator.validate(actualStage, definition);

        assertEquals("StageDefinition expected 1 parameter(s) but found 0 instead.", report.getDetails().get(0));
    }

    @Test
    public void validate_whenActualStageParametersIsNotEmptyAndDefinitionParametersIsEmpty_expectedFailReasonReturned() {
        when(definition.getParameters()).thenReturn(new ArrayList<>());

        ValidationReport report = stageValidator.validate(actualStage, definition);

        assertEquals("StageDefinition expected 0 parameter(s) but found 1 instead.", report.getDetails().get(0));
    }

    @Test
    public void validate_whenParameterDefinitionsDoesNotMatchParametersSize_expectedFailReasonReturned() {
        Parameter param2 = mock(Parameter.class);
        when(param2.getName()).thenReturn("param2");
        when(param2.getValue()).thenReturn("value2");
        when(param2.getAdditionalDetails()).thenReturn(additionalDetails);
        when(actualStage.getParameters()).thenReturn(Arrays.asList(param, param2));

        ValidationReport report = stageValidator.validate(actualStage, definition);

        assertEquals("StageDefinition expected 1 parameter(s) but found 2 instead.", report.getDetails().get(0));
    }

    @Test
    public void validate_whenNoParametersNameMatchParameterDefinitionName_expectedFailReasonReturned() {
        when(paramDef.getName()).thenReturn("otherParamName");

        ValidationReport report = stageValidator.validate(actualStage, definition);

        assertEquals("Expected a parameter named otherParamName but none provided.", report.getDetails().get(0));
    }

    @Test
    public void validate_whenParameterTypeComparatorReturnsFalse_expectedFailReasonReturned() {
        when(parameterTypeComparator.isValueValid(any(), any())).thenReturn(false);

        ValidationReport report = stageValidator.validate(actualStage, definition);

        String expectedDetail = String.format(
                "Specified type for parameter %s does not match value. Type was %s and raw value : %s",
                paramName1,
                parameterType,
                param.getValue()
        );

        assertEquals(1, report.getDetails().size());
        assertEquals(expectedDetail, report.getDetails().get(0));
    }

    @Test
    public void validate_whenMatchedParameterAdditionalDetailsIsNull_expectedFailReasonReturned() {
        when(param.getAdditionalDetails()).thenReturn(null);

        ValidationReport report = stageValidator.validate(actualStage, definition);

        assertEquals(String.format(
                        "Technical issue. additionalDetails for parameter %s is null, should at least be empty list.",
                        paramName1),
                report.getDetails().get(0));
    }

    @Test
    public void validate_whenMatchedParameterValueIsNull_expectedFailReasonReturned() {
        when(param.getValue()).thenReturn(null);

        ValidationReport report = stageValidator.validate(actualStage, definition);

        assertEquals(String.format("Value of parameter %s is null, should be at least an empty string.", paramName1), report.getDetails().get(0));
    }
}
