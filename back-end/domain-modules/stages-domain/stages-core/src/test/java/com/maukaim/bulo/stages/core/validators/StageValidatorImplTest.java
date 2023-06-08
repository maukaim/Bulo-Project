package com.maukaim.bulo.stages.core.validators;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.common.utils.ParameterTypeComparator;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.models.stage.Parameter;
import com.maukaim.bulo.stages.models.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StageValidatorImplTest {
    private StageValidatorImpl stageValidator;
    private Stage actualStage;
    private StageDefinition definition;
    private ParameterType parameterType;
    private MockedStatic<ParameterTypeComparator> mockedComparator;

    @BeforeEach
    public void setup() {
        stageValidator = new StageValidatorImpl();
        actualStage = mock(Stage.class);
        definition = mock(StageDefinition.class);
        parameterType = mock(ParameterType.class);
        mockedComparator = Mockito.mockStatic(ParameterTypeComparator.class);
        mockedComparator.when(() -> ParameterTypeComparator.isValueValid(any(), any())).thenReturn(true);
    }

    @AfterEach
    public void tearDown() {
        mockedComparator.close();
    }

    @Test
    public void validate_whenAllSuccess_DEFAULT_SUCCESS_REPORTReturned() {
        Parameter param = mock(Parameter.class);
        ParameterDefinition paramDef = mock(ParameterDefinition.class);
        when(param.getName()).thenReturn("param1");
        when(param.getValue()).thenReturn("value1");
        when(param.getAdditionalDetails()).thenReturn("");
        when(paramDef.getName()).thenReturn("param1");
        when(paramDef.getParameterType()).thenReturn(parameterType);
        mockedComparator.when(() -> ParameterTypeComparator.isValueValid(any(), any())).thenReturn(true);

        when(actualStage.getParameters()).thenReturn(Collections.singletonList(param));
        when(definition.getParameters()).thenReturn(Collections.singletonList(paramDef));

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
        // setup
        Parameter param = mock(Parameter.class);
        when(param.getName()).thenReturn("param1");
        when(param.getValue()).thenReturn("value1");
        when(param.getAdditionalDetails()).thenReturn("");
        when(actualStage.getParameters()).thenReturn(Collections.singletonList(param));
        when(definition.getParameters()).thenReturn(null);

        // exercise
        ValidationReport report = stageValidator.validate(actualStage, definition);

        // verify
        assertEquals("Unexpected error occurred during validation process of this stage, on behalf of definition null", report.getDetails().get(0));
    }


    @Test
    public void validate_whenActualStageParametersIsEmptyAndDefinitionParametersIsNotEmpty_expectedFailReasonReturned() {
        // setup
        when(actualStage.getParameters()).thenReturn(new ArrayList<>());
        when(definition.getParameters()).thenReturn(Collections.singletonList(mock(ParameterDefinition.class)));

        // exercise
        ValidationReport report = stageValidator.validate(actualStage, definition);

        // verify
        assertEquals("StageDefinition expected 1 parameter(s) but found 0 instead.", report.getDetails().get(0));
    }

    @Test
    public void validate_whenActualStageParametersIsNotEmptyAndDefinitionParametersIsEmpty_expectedFailReasonReturned() {
        // setup
        Parameter param = mock(Parameter.class);
        when(param.getName()).thenReturn("param1");
        when(param.getValue()).thenReturn("value1");
        when(param.getAdditionalDetails()).thenReturn("");
        when(actualStage.getParameters()).thenReturn(Collections.singletonList(param));
        when(definition.getParameters()).thenReturn(new ArrayList<>());

        // exercise
        ValidationReport report = stageValidator.validate(actualStage, definition);

        // verify
        assertEquals("StageDefinition expected 0 parameter(s) but found 1 instead.", report.getDetails().get(0));
    }

    @Test
    public void validate_whenParameterDefinitionsDoesNotMatchParametersSize_expectedFailReasonReturned() {
        // setup
        Parameter param1 = mock(Parameter.class);
        Parameter param2 = mock(Parameter.class);
        ParameterDefinition paramDef = mock(ParameterDefinition.class);
        when(param1.getName()).thenReturn("param1");
        when(param1.getValue()).thenReturn("value1");
        when(param1.getAdditionalDetails()).thenReturn("");
        when(param2.getName()).thenReturn("param2");
        when(param2.getValue()).thenReturn("value2");
        when(param2.getAdditionalDetails()).thenReturn("");
        when(paramDef.getName()).thenReturn("param1");
        when(paramDef.getParameterType()).thenReturn(parameterType);
        when(actualStage.getParameters()).thenReturn(Arrays.asList(param1, param2));
        when(definition.getParameters()).thenReturn(Collections.singletonList(paramDef));

        // exercise
        ValidationReport report = stageValidator.validate(actualStage, definition);

        // verify
        assertEquals("StageDefinition expected 1 parameter(s) but found 2 instead.", report.getDetails().get(0));
    }

    @Test
    public void validate_whenNoParametersNameMatchParameterDefinitionName_expectedFailReasonReturned() {
        // setup
        Parameter param = mock(Parameter.class);
        ParameterDefinition paramDef = mock(ParameterDefinition.class);
        when(param.getName()).thenReturn("param1");
        when(param.getValue()).thenReturn("value1");
        when(param.getAdditionalDetails()).thenReturn("");
        when(paramDef.getName()).thenReturn("param2");
        when(paramDef.getParameterType()).thenReturn(parameterType);
        when(actualStage.getParameters()).thenReturn(Collections.singletonList(param));
        when(definition.getParameters()).thenReturn(Collections.singletonList(paramDef));

        // exercise
        ValidationReport report = stageValidator.validate(actualStage, definition);

        // verify
        assertEquals("Expected a parameter named param2 but none provided.", report.getDetails().get(0));
    }

    @Test
    public void validate_whenParameterTypeComparatorReturnsFalse_expectedFailReasonReturned() {
        Parameter param = mock(Parameter.class);
        ParameterDefinition paramDef = mock(ParameterDefinition.class);
        when(param.getName()).thenReturn("param1");
        when(param.getValue()).thenReturn("value1");
        when(param.getAdditionalDetails()).thenReturn("");
        when(paramDef.getName()).thenReturn("param1");
        when(paramDef.getParameterType()).thenReturn(parameterType);
        mockedComparator.when(() -> ParameterTypeComparator.isValueValid(any(), any())).thenReturn(false);

        when(actualStage.getParameters()).thenReturn(Collections.singletonList(param));
        when(definition.getParameters()).thenReturn(Collections.singletonList(paramDef));

        ValidationReport report = stageValidator.validate(actualStage, definition);

        String expectedDetail = String.format(
                "Specified type for parameter %s does not match value. Type was %s and raw value : %s",
                param.getName(),
                parameterType,
                param.getValue()
        );

        assertEquals(1, report.getDetails().size());
        assertEquals(expectedDetail, report.getDetails().get(0));
    }

    @Test
    public void validate_whenMatchedParameterAdditionalDetailsIsNull_expectedFailReasonReturned() {
        // setup
        Parameter param = mock(Parameter.class);
        ParameterDefinition paramDef = mock(ParameterDefinition.class);
        when(param.getName()).thenReturn("param1");
        when(param.getValue()).thenReturn("value1");
        when(param.getAdditionalDetails()).thenReturn(null);
        when(paramDef.getName()).thenReturn("param1");
        when(paramDef.getParameterType()).thenReturn(parameterType);
        when(actualStage.getParameters()).thenReturn(Collections.singletonList(param));
        when(definition.getParameters()).thenReturn(Collections.singletonList(paramDef));

        // exercise
        ValidationReport report = stageValidator.validate(actualStage, definition);

        // verify
        assertEquals("Technical issue. additionalDetails for parameter param1 is null, should at least be empty list.", report.getDetails().get(0));
    }

    @Test
    public void validate_whenMatchedParameterValueIsNull_expectedFailReasonReturned() {
        // setup
        Parameter param = mock(Parameter.class);
        ParameterDefinition paramDef = mock(ParameterDefinition.class);
        when(param.getName()).thenReturn("param1");
        when(param.getValue()).thenReturn(null);
        when(param.getAdditionalDetails()).thenReturn("");
        when(paramDef.getName()).thenReturn("param1");
        when(paramDef.getParameterType()).thenReturn(parameterType);
        when(actualStage.getParameters()).thenReturn(Collections.singletonList(param));
        when(definition.getParameters()).thenReturn(Collections.singletonList(paramDef));

        // exercise
        ValidationReport report = stageValidator.validate(actualStage, definition);

        // verify
        assertEquals("Value of parameter param1 is null, should be at least an empty string.", report.getDetails().get(0));
    }
}
