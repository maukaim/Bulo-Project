package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.common.utils.ParameterTypeComparator;
import com.maukaim.bulo.flows.data.models.definition.ParameterDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.stage.Parameter;
import com.maukaim.bulo.flows.data.models.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StageParameterValidatorImplTest {
    private final ParameterTypeComparator parameterTypeComparator = mock(ParameterTypeComparator.class);
    private StageParameterValidatorImpl stageParameterValidator;
    private final Stage stage = mock(Stage.class);
    private final String parameterName = "name";
    private final String stageId = "stageId";
    private final String parameterValue = "value";
    private final Parameter parameter = mock(Parameter.class);
    private final ParameterType parameterType = mock(ParameterType.class);
    private final ParameterDefinition parameterDefinition = mock(ParameterDefinition.class);
    private final StageDefinition stageDefinition = mock(StageDefinition.class);

    @BeforeEach
    void init() throws FlowValidationException {
        when(parameterTypeComparator.isValueValid(any(), any())).thenReturn(true);
        stageParameterValidator = new StageParameterValidatorImpl(parameterTypeComparator);
        when(stage.getParameters()).thenReturn(List.of(parameter));
        when(stageDefinition.getParameters()).thenReturn(List.of(parameterDefinition));

        when(parameterDefinition.getName()).thenReturn(parameterName);
        when(parameter.getName()).thenReturn(parameterName);
        when(parameterDefinition.getParameterType()).thenReturn(parameterType);
        when(parameter.getValue()).thenReturn(parameterValue);
        when(stage.getStageId()).thenReturn(stageId);
    }

    @Test
    void validate_success() throws FlowValidationException {
        stageParameterValidator.validate(stage, stageDefinition);
        verify(parameterTypeComparator).isValueValid(parameterValue, parameterType);
    }

    @Test
    void validate_whenParametersMissingButExpected_thenThow() {
        when(stage.getParameters()).thenReturn(null);

        FlowValidationException exception = assertThrows(FlowValidationException.class, () -> stageParameterValidator.validate(stage, stageDefinition));

        assertEquals(exception.getMessage(), "No parameters provided, but some expected. Stage " + stageId);
        verify(parameterTypeComparator, never()).isValueValid(parameterValue, parameterType);
    }

    @Test
    void validate_whenPameterDefinitionDeclaresName_butParametersProvidedDoesNotMatch_thenThow() {
        when(parameter.getName()).thenReturn("otherParamName");

        FlowValidationException exception = assertThrows(FlowValidationException.class, () -> stageParameterValidator.validate(stage, stageDefinition));

        assertEquals(exception.getMessage(), "Parameter " + parameterName + " expected in Stage " + stageId + " but not provided.");
        verify(parameterTypeComparator, never()).isValueValid(parameterValue, parameterType);
    }

    @Test
    void validate_whenParameterValueNotValid_thenThow() {
        when(parameterTypeComparator.isValueValid(any(), any())).thenReturn(false);

        FlowValidationException exception = assertThrows(FlowValidationException.class, () -> stageParameterValidator.validate(stage, stageDefinition));

        assertEquals(exception.getMessage(), "In stage " + stageId + ", parameter " + parameterName + "'s value does not match declared type " + parameterType + ". Value is: " + parameterValue);
        verify(parameterTypeComparator).isValueValid(parameterValue, parameterType);
    }

}