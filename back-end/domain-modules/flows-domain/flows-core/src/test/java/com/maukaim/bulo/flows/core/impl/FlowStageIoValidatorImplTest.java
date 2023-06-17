package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.common.utils.IoTypeComparator;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.flows.core.DefinitionService;
import com.maukaim.bulo.flows.core.FlowValidationException;
import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageOutputDefinition;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FlowStageIoValidatorImplTest {
    private DefinitionService definitionService;
    private StageService stageService;
    private IoTypeComparator ioTypeComparator;
    private FlowStageIoValidatorImpl flowStageIoValidator;

    private final String inputId = "inputId";
    private final String stageId = "stageId";
    private final String stageDefinitionId = "stageDefinitionId";
    private final StageInputDefinition stageInputDefinition = mock(StageInputDefinition.class);
    private final StageOutputDefinition stageOutputDefinition = mock(StageOutputDefinition.class);
    private final InputProvider inputProvider = mock(InputProvider.class);
    private final ContextStageId contextStageId = mock(ContextStageId.class);
    private final Stage stage = mock(Stage.class);
    private final StageDefinition stageDefinition = mock(StageDefinition.class);

    @BeforeEach
    void init() {
        this.definitionService = mock(DefinitionService.class);
        this.stageService = mock(StageService.class);
        this.ioTypeComparator = mock(IoTypeComparator.class);
        this.flowStageIoValidator = new FlowStageIoValidatorImpl(this.definitionService, this.stageService, this.ioTypeComparator);
        successSetUp();
    }

    @Test
    void validate_whenStageInputDefinitionCanBeMultiple_thenSuccess() throws FlowValidationException {
        successSetupWhenCanBeMultiple();
        flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider));
    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_thenSuccess() throws FlowValidationException {
        successSetupWhenCantBeMultiple();
        flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider));
    }

    @Test
    void validate_whenInputProvidersIsNull_andStageInputDefinitionIsRequired_thenThrow() {
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, null));

        assertEquals("Problem, we expect this input to get at least 1 provider: " + inputId, exception.getMessage());
    }

    @Test
    void validate_whenInputProvidersIsEmpty_andStageInputDefinitionIsRequired_thenThrow() {
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of()));

        assertEquals("Problem, we expect this input to get at least 1 provider: " + inputId, exception.getMessage());
    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_andThereIsMultipleInputProviders_thenThrowException() {
        successSetupWhenCantBeMultiple();
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider, mock(InputProvider.class))));

        assertEquals("Input with name " + inputId + " expect only one element, but got multiples providers.", exception.getMessage());
    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_andInputProviderHasMultipleOutputIds_thenThrowException() {
        successSetupWhenCantBeMultiple();
        Set<String> outputIds = Set.of(inputId, "anotherOutputId");
        when(inputProvider.getOutputIds()).thenReturn(outputIds);
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider)));

        assertEquals("Too much outputIds. Expected 1, got " + outputIds.size(), exception.getMessage());
    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_andInputProviderDefinitionDoesNotHaveTheExpectedOutput_thenThrowException() throws FlowValidationException {
        successSetupWhenCantBeMultiple();
        flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider));
        when(stageDefinition.getOutputsByName()).thenReturn(Map.of());
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider)));

        assertEquals("Output " + inputId + " expected in definition " + stageDefinitionId + ". But not found.", exception.getMessage());

    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_andStageOutputCanBeMultiple_thenThrowException() throws FlowValidationException {
        successSetupWhenCantBeMultiple();
        when(stageOutputDefinition.canBeMultiple()).thenReturn(true);
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider)));

        assertEquals("Input Provider potentially provide a collection. Not allowed here.", exception.getMessage());
    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_andIoTypeComparatorReturnsFalse_thenThrowException() throws FlowValidationException {
        successSetupWhenCantBeMultiple();
        when(ioTypeComparator.areEquals(any(), any(), anyBoolean())).thenReturn(false);
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider)));

        assertEquals("Output of ancestor is type " + stageOutputDefinition.getType() + " but input expected should be type " + stageInputDefinition.getType(), exception.getMessage());
    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_andInputProviderDoesNotHaveFlowStageId_thenThrowException() throws FlowValidationException {
        successSetupWhenCantBeMultiple();
        when(inputProvider.getFlowStageId()).thenReturn(null);
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider)));

        assertEquals("Input Provider has no existing flowStageId.", exception.getMessage());
    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_andInputProvidersFlowStageIdDoesNotHaveStageId_thenThrowException() throws FlowValidationException {
        successSetupWhenCantBeMultiple();
        when(contextStageId.getStageId()).thenReturn(null);
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider)));

        assertEquals("Input Provider has no existing flowStageId.", exception.getMessage());
    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_andInputProviderDoesNotHaveStageInStore_thenThrowException() throws FlowValidationException {
        successSetupWhenCantBeMultiple();
        when(stageService.getById(stageId)).thenReturn(null);
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider)));

        assertEquals("No Stage for id " + stageId, exception.getMessage());
    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_andInputProviderStageDoesNotHaveDefinitionInStore_thenThrowException() throws FlowValidationException {
        successSetupWhenCantBeMultiple();
        when(definitionService.getById(stageDefinitionId)).thenReturn(null);
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider)));

        assertEquals("No Definition for Stage " + stageId, exception.getMessage());
    }

    @Test
    void validate_whenStageInputDefinitionShouldBeSingle_andInputIsNotRequired_thenStillSuccess() throws FlowValidationException {
        successSetupWhenCantBeMultiple();
        when(stageInputDefinition.isRequired()).thenReturn(false);

        flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider));
    }

    @Test
    void validate_whenStageInputDefinitionCanBeMultiple_andInputIsNotRequired_thenStillSuccess() throws FlowValidationException {
        successSetupWhenCanBeMultiple();
        when(stageInputDefinition.isRequired()).thenReturn(false);

        flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider));
    }

    @Test
    void validate_whenStageInputDefinitionCanBeMultiple_and_thenThrow() throws FlowValidationException {
        successSetupWhenCanBeMultiple();
        when(stageOutputDefinition.isAlwaysPresent()).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> flowStageIoValidator.validate(inputId, stageInputDefinition, Set.of(inputProvider)));
        assertEquals("No input provider guaranty there will ALWAYS be a data", exception.getMessage());
    }

    private void successSetUp() {
        when(stageInputDefinition.isRequired()).thenReturn(true);
        when(inputProvider.getFlowStageId()).thenReturn(contextStageId);

        when(contextStageId.getStageId()).thenReturn(stageId);
        when(stageService.getById(stageId)).thenReturn(stage);
        when(stage.getDefinitionId()).thenReturn(stageDefinitionId);
        when(stage.getStageId()).thenReturn(stageId);
        when(definitionService.getById(stage.getDefinitionId())).thenReturn(stageDefinition);
        when(stageDefinition.getOutputsByName()).thenReturn(Map.of(inputId, stageOutputDefinition));

        when(inputProvider.getOutputIds()).thenReturn(Set.of(inputId));

        when(stageOutputDefinition.canBeMultiple()).thenReturn(false);
        when(ioTypeComparator.areEquals(any(), any(), anyBoolean())).thenReturn(true);
        when(stageDefinition.getStageDefinitionId()).thenReturn(stageDefinitionId);
    }

    private void successSetupWhenCantBeMultiple() {
        when(stageInputDefinition.canBeMultiple()).thenReturn(false);
    }

    private void successSetupWhenCanBeMultiple() {
        when(stageInputDefinition.canBeMultiple()).thenReturn(true);
        when(stageOutputDefinition.isAlwaysPresent()).thenReturn(true);
    }
}