package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.common.utils.IoTypeComparator;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.definition.StageOutputDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.OutputProvider;
import com.maukaim.bulo.definitions.data.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OutputsFSDValidatorTest {
    private StageStore stageStore;
    private StageDefinitionStore stageDefinitionStore;
    private OutputsFSDValidator outputsFSDValidator;
    private IoTypeComparator ioTypeComparator;

    private final String stageId = "stageId";
    private final String definitionId = "definitionId";
    private final String outputId = "outputId";
    private final FsStage fsStage = mock(FsStage.class);
    private final ContextStageId contextStageId = ContextStageId.of(stageId);
    private final Stage stage = mock(Stage.class);
    private final OutputProvider outputProvider = mock(OutputProvider.class);
    private final FunctionalStageDefinition definition = mock(FunctionalStageDefinition.class);
    private final FunctionalStageDefinition definitionOfSubStage = mock(FunctionalStageDefinition.class);
    private final StageOutputDefinition stageOutputDefinition = mock(StageOutputDefinition.class);
    private final StageOutputDefinition subStageOutputDefinition = mock(StageOutputDefinition.class);

    @BeforeEach
    void setUp() {
        stageStore = mock(StageStore.class);
        stageDefinitionStore = mock(StageDefinitionStore.class);
        ioTypeComparator = mock(IoTypeComparator.class);
        outputsFSDValidator = new OutputsFSDValidator(stageStore, stageDefinitionStore, ioTypeComparator);

        when(stageStore.getById(stageId)).thenReturn(stage);
        when(stage.getDefinitionId()).thenReturn(definitionId);
        when(stageDefinitionStore.getById(definitionId)).thenReturn(definitionOfSubStage);

        when(outputProvider.getOutputIds()).thenReturn(Set.of(outputId));
        when(definition.getOutputsByName()).thenReturn(Map.of(outputId, stageOutputDefinition));

        whenBothFunctionalSubStagesAndOutputProvidersMatches();
        whenOutputsByNameMatchesLeavesDefinitions();
    }

    @Test
    void validate_whenAllValidationPass_thenSuccess() {
        boolean result = outputsFSDValidator.validate(definition);

        assertTrue(result);
        verify(stageStore, times(1)).getById(any());
        verify(stageDefinitionStore, times(1)).getById(any());
        verify(ioTypeComparator, times(1)).areEquals(any(), any(), anyBoolean());
    }

    @Test
    void validate_whenSubStageHaveMultipleOutputNotNecessarilyUsedByFunctionalStage_thenSuccess() {
        when(definitionOfSubStage.getOutputsByName()).thenReturn(Map.of("anotherOutputId", subStageOutputDefinition, outputId, subStageOutputDefinition));

        boolean result = outputsFSDValidator.validate(definition);

        assertTrue(result);
        verify(stageStore, times(1)).getById(any());
        verify(stageDefinitionStore, times(1)).getById(any());
        verify(ioTypeComparator, times(1)).areEquals(any(), any(), anyBoolean());
    }


    @Test
    void validate_whenFunctionalSubStagesAndOutputProvidersAndOutputByNameAreEmpty_thenSuccess() {
        when(definition.getFunctionalSubStages()).thenReturn(Collections.emptySet());
        when(definition.getOutputProviders()).thenReturn(Collections.emptySet());
        when(definition.getOutputsByName()).thenReturn(Collections.emptyMap());

        boolean result = outputsFSDValidator.validate(definition);

        assertTrue(result);
        verify(stageStore, never()).getById(any());
        verify(stageDefinitionStore, never()).getById(any());
        verify(ioTypeComparator, never()).areEquals(any(), any(), anyBoolean());
    }

    @Test
    void validate_whenOutputProviderDoesNotMatchAnyFunctionalSubStages_throwsExpectedError() {
        when(fsStage.getContextualizedId()).thenReturn(ContextStageId.of("anotherStageId"));

        Exception exception = assertThrows(RuntimeException.class, () -> outputsFSDValidator.validate(definition));
        assertEquals("Following output provider is not an existing substage " + contextStageId, exception.getMessage());
    }

    @Test
    void validate_whenStageStoreDoesNotHaveTheStageForStageId_throwsExpectedError() {
        when(stageStore.getById(stageId)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> outputsFSDValidator.validate(definition));
        assertEquals("Stage referenced as outputProvider, but does not exist.", exception.getMessage());
    }

    @Test
    void validate_whenStageDefinitionStoreDoesNotHaveTheStageForDefinitionId_throwsExpectedError() {
        when(stageDefinitionStore.getById(definitionId)).thenReturn(null);
        Exception exception = assertThrows(RuntimeException.class, () -> outputsFSDValidator.validate(definition));
        assertEquals("Stage declares definition" + stage.getDefinitionId() + ", but it does not exist.", exception.getMessage());
    }

    @Test
    void validate_whenActualOutputsDoesNotHaveSameSizeHasLeavesDefinitionsByOutputId_throwsExpectedError() {
        when(definition.getOutputsByName()).thenReturn(Collections.emptyMap());
        Exception exception = assertThrows(RuntimeException.class, () -> outputsFSDValidator.validate(definition));
        assertEquals("Actually declared " + definition.getOutputsByName().size() + " outputs, but expected:" + 1, exception.getMessage());

        when(definition.getOutputsByName()).thenReturn(Map.of("anotherOutputId", subStageOutputDefinition, outputId, subStageOutputDefinition));
        Exception exception2 = assertThrows(RuntimeException.class, () -> outputsFSDValidator.validate(definition));
        assertEquals("Actually declared " + definition.getOutputsByName().size() + " outputs, but expected:" + 1, exception2.getMessage());
    }

    @Test
    void validate_whenFsOutputDefinitionIsNotAlwaysPresent_andIoTypesAreDifferent_throwsExpectedError() {
        when(stageOutputDefinition.isAlwaysPresent()).thenReturn(false);
        when(ioTypeComparator.areEquals(any(), any(), anyBoolean())).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> outputsFSDValidator.validate(definition));
        assertEquals("Declared output named " + outputId + " but does not match expected type regarding the declared providers:" + List.of(definitionOfSubStage), exception.getMessage());
    }

    @Test
    void validate_whenFsOutputDefinitionIsNotAlwaysPresent_andCanBeMultipleIsDifferent_throwsExpectedError() {
        when(stageOutputDefinition.isAlwaysPresent()).thenReturn(false);
        when(stageOutputDefinition.canBeMultiple()).thenReturn(true);
        when(subStageOutputDefinition.canBeMultiple()).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> outputsFSDValidator.validate(definition));
        assertEquals("Declared output named " + outputId + " but does not match expected type regarding the declared providers:" + List.of(definitionOfSubStage), exception.getMessage());
    }

    @Test
    void validate_whenFsOutputDefinitionIsAlwaysPresent_andIoTypesAreDifferent_throwsExpectedError() {
        when(stageOutputDefinition.isAlwaysPresent()).thenReturn(true);
        when(ioTypeComparator.areEquals(any(), any(), anyBoolean())).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> outputsFSDValidator.validate(definition));
        assertEquals("Declared output named " + outputId + " but does not match expected type regarding the declared providers:" + List.of(definitionOfSubStage), exception.getMessage());
    }

    @Test
    void validate_whenFsOutputDefinitionIsAlwaysPresent_andCanBeMultipleIsDifferent_throwsExpectedError() {
        when(stageOutputDefinition.isAlwaysPresent()).thenReturn(true);
        when(stageOutputDefinition.canBeMultiple()).thenReturn(true);
        when(subStageOutputDefinition.canBeMultiple()).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> outputsFSDValidator.validate(definition));
        assertEquals("Declared output named " + outputId + " but does not match expected type regarding the declared providers:" + List.of(definitionOfSubStage), exception.getMessage());
    }

    @Test
    void validate_whenFsOutputDefinitionIsAlwaysPresent_andNbLeavesWithAlwaysPresentOutputIsZero_throwsExpectedError() {
        when(stageOutputDefinition.isAlwaysPresent()).thenReturn(true);
        when(subStageOutputDefinition.isAlwaysPresent()).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> outputsFSDValidator.validate(definition));
        assertEquals("Functional Stage declares output " + outputId + " as always present, but no output provider could guaranty it.", exception.getMessage());
    }

    private void whenOutputsByNameMatchesLeavesDefinitions() {
        when(stageOutputDefinition.canBeMultiple()).thenReturn(true);
        when(stageOutputDefinition.isAlwaysPresent()).thenReturn(true);
        when(subStageOutputDefinition.canBeMultiple()).thenReturn(true);
        when(subStageOutputDefinition.isAlwaysPresent()).thenReturn(true);

        IoType stageIoType = mock(IoType.class);
        when(stageOutputDefinition.getType()).thenReturn(stageIoType);
        IoType subStageIoType = mock(IoType.class);
        when(subStageOutputDefinition.getType()).thenReturn(subStageIoType);

        when(definitionOfSubStage.getOutputsByName()).thenReturn(Map.of(outputId, subStageOutputDefinition));
        when(ioTypeComparator.areEquals(any(), any(), anyBoolean())).thenReturn(true);
    }

    private void whenBothFunctionalSubStagesAndOutputProvidersMatches() {
        when(definition.getFunctionalSubStages()).thenReturn(Collections.singleton(fsStage));
        when(definition.getOutputProviders()).thenReturn(Collections.singleton(outputProvider));

        when(outputProvider.getContextStageId()).thenReturn(contextStageId);
        when(fsStage.getContextualizedId()).thenReturn(contextStageId);
    }
}