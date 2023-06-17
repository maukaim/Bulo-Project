package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.flows.core.DefinitionService;
import com.maukaim.bulo.flows.core.FlowStageIoValidator;
import com.maukaim.bulo.flows.core.FlowValidationException;
import com.maukaim.bulo.flows.core.FlowValidator;
import com.maukaim.bulo.flows.core.StageInputValidator;
import com.maukaim.bulo.flows.core.StageParameterValidator;
import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.InputProvider;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.flows.data.models.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FlowValidatorImplTest {
    private StageService stageService;
    private DefinitionService definitionService;
    private StageParameterValidator stageParameterValidator;
    private FlowStageIoValidator flowStageIoValidator;
    private StageInputValidator stageInputValidator;
    private AcyclicValidator<ContextStageId> acyclicValidator;

    private final Flow flow = mock(Flow.class);
    private final FlowStage flowStage = mock(FlowStage.class);
    private final FlowStage inputProviderAsFlowStage = mock(FlowStage.class);
    private final Stage stage = mock(Stage.class);
    private final Stage inputProviderStage = mock(Stage.class);
    private final ContextStageId flowStageContextId = mock(ContextStageId.class);
    private final IoDependency ioDependency = mock(IoDependency.class);
    private final InputProvider inputProvider = mock(InputProvider.class);
    private final ContextStageId inputProviderContextStageId = mock(ContextStageId.class);
    private final String stageId = "stageId";
    private final String inputProviderStageId = "inputProviderStageId";
    private final String definitionId = "definitionId";
    private final String inputProviderDefinitionId = "inputProviderDefinitionId";
    private final String inputId = "inputId";
    private final StageDefinition stageDefinition = mock(StageDefinition.class);
    private final StageDefinition inputProviderStageDefinition = mock(StageDefinition.class);
    private final StageInputDefinition stageInputDefinition = mock(StageInputDefinition.class);

    private FlowValidator flowValidator;

    @BeforeEach
    void init() {
        this.stageService = mock(StageService.class);
        this.definitionService = mock(DefinitionService.class);
        this.stageParameterValidator = mock(StageParameterValidator.class);
        this.flowStageIoValidator = mock(FlowStageIoValidator.class);
        this.stageInputValidator = mock(StageInputValidator.class);
        this.acyclicValidator = mock(AcyclicValidator.class);

        this.flowValidator = new FlowValidatorImpl(
                this.stageService,
                this.definitionService,
                this.stageParameterValidator,
                this.flowStageIoValidator,
                this.stageInputValidator,
                this.acyclicValidator
        );
        successSetup();

    }

    private void successSetup() {
        when(flow.getFlowStages()).thenReturn(Set.of(flowStage, inputProviderAsFlowStage));
        when(flowStage.getFlowStageId()).thenReturn(flowStageContextId);
        when(flowStage.getIoDependencies()).thenReturn(Set.of(ioDependency));
        when(inputProviderAsFlowStage.getFlowStageId()).thenReturn(inputProviderContextStageId);
        when(inputProviderAsFlowStage.getIoDependencies()).thenReturn(Set.of());

        when(flowStageContextId.getStageId()).thenReturn(stageId);
        when(inputProviderContextStageId.getStageId()).thenReturn(inputProviderStageId);

        when(ioDependency.getInputProviders()).thenReturn(Set.of(inputProvider));
        when(ioDependency.getInputId()).thenReturn(inputId);
        when(inputProvider.getFlowStageId()).thenReturn(inputProviderContextStageId);

        when(stageService.getById(stageId)).thenReturn(stage);
        when(stageService.getById(inputProviderStageId)).thenReturn(inputProviderStage);

        when(stage.getDefinitionId()).thenReturn(definitionId);
        when(inputProviderStage.getDefinitionId()).thenReturn(inputProviderDefinitionId);
        when(definitionService.getById(definitionId)).thenReturn(stageDefinition);
        when(definitionService.getById(inputProviderDefinitionId)).thenReturn(inputProviderStageDefinition);
        when(stage.getStageId()).thenReturn(stageId);

        when(stageDefinition.getInputsByName()).thenReturn(Map.of(inputId, stageInputDefinition));
    }

    @Test
    void validate_success() throws FlowValidationException {
        flowValidator.validate(flow);
    }

    @Test
    void validate_whenDependencyGraphIsEmpty_thenThrowException() {
        when(flow.getFlowStages()).thenReturn(Set.of());
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class, () -> flowValidator.validate(flow));
        Assertions.assertEquals("Dependency Graph can't be null or empty", exception.getMessage());
    }

    @Test
    void validate_whenDependencyGraphIsNull_thenThrowException() {
        when(flow.getFlowStages()).thenReturn(null);
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class, () -> flowValidator.validate(flow));
        Assertions.assertEquals("Dependency Graph can't be null or empty", exception.getMessage());
    }

    @Test
    void validate_whenAcyclicValidatorThrows_thenThrowException() {
        String errorMessage = "Error message";
        doThrow(new RuntimeException(errorMessage)).when(acyclicValidator).validate(anyMap());
        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class, () -> flowValidator.validate(flow));
        Assertions.assertEquals("Acyclic test failed on dependency graph. Following reason: " + errorMessage, exception.getMessage());
    }

    @Test
    void validate_whenStageToValidateNotFound_thenThrowException() {
        when(stageService.getById(stageId)).thenReturn(null);

        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class, () -> flowValidator.validate(flow));
        Assertions.assertEquals("A Stage Id is not recognized, so we can't validate the Flow: " + stageId, exception.getMessage());
    }

    @Test
    void validate_whenTheAncestorIsNotInDeclaredFlowStages_thenThrowException() {
        when(flow.getFlowStages()).thenReturn(Set.of(flowStage));

        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class, () -> flowValidator.validate(flow));
        Assertions.assertEquals(inputProviderContextStageId + " marked as ancestor of " + flowStageContextId + " but not present itself in the Flow.", exception.getMessage());
    }

    @Test
    void validate_whenStageDefinitionNotFound_thenThrowException() {
        when(definitionService.getById(definitionId)).thenReturn(null);

        FlowValidationException exception = Assertions.assertThrows(FlowValidationException.class, () -> flowValidator.validate(flow));
        Assertions.assertEquals("No definition for stage " + stageId + " so we can't validate the Flow.", exception.getMessage());
    }
}