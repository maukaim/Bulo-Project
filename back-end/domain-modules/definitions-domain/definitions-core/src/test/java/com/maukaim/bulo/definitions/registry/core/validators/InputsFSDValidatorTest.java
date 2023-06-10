package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.common.utils.IoTypeComparator;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.definition.StageInputDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.InputProvider;
import com.maukaim.bulo.definitions.data.definition.functional.IoDependency;
import com.maukaim.bulo.definitions.data.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
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

class InputsFSDValidatorTest {
    private StageStore stageStore;
    private StageDefinitionStore stageDefinitionStore;
    private InputsFSDValidator inputsFSDValidator;
    private IoTypeComparator ioTypeComparator;

    private final String stageId = "stageId";
    private final String definitionId = "definitionId";
    private final String inputId = "outputId";
    private final FsStage fsStage = mock(FsStage.class);
    private final IoDependency ioDependency = mock(IoDependency.class);
    private final ContextStageId contextStageId = ContextStageId.of(stageId);
    private final Stage subStage = mock(Stage.class);
    private final FunctionalStageDefinition definition = mock(FunctionalStageDefinition.class);
    private final FunctionalStageDefinition definitionOfSubStage = mock(FunctionalStageDefinition.class);
    private final StageInputDefinition stageInputDefinition = mock(StageInputDefinition.class);
    private final StageInputDefinition subStageInputDefinition = mock(StageInputDefinition.class);


    @BeforeEach
    void init() {
        stageDefinitionStore = mock(StageDefinitionStore.class);
        stageStore = mock(StageStore.class);
        ioTypeComparator = mock(IoTypeComparator.class);
        this.inputsFSDValidator = new InputsFSDValidator(stageDefinitionStore, stageStore, ioTypeComparator);

        when(definition.getFunctionalSubStages()).thenReturn(Set.of(fsStage));
        when(definition.getInputsByName()).thenReturn(Map.of(inputId, stageInputDefinition));

        whenHasRootsInputDefinition();
        whenInputIsNotMandatoryAndValid();
    }

    private void whenInputIsNotMandatoryAndValid() {
        when(stageInputDefinition.isRequired()).thenReturn(false);
        when(subStageInputDefinition.isRequired()).thenReturn(false);
        when(definitionOfSubStage.getInputsByName()).thenReturn(Map.of(inputId, subStageInputDefinition));

        when(stageInputDefinition.canBeMultiple()).thenReturn(false);
        when(subStageInputDefinition.canBeMultiple()).thenReturn(false);

        IoType stageIoType = mock(IoType.class);
        IoType subStageIoType = mock(IoType.class);
        when(stageInputDefinition.getType()).thenReturn(stageIoType);
        when(subStageInputDefinition.getType()).thenReturn(subStageIoType);
        when(ioTypeComparator.areEquals(any(), any(), anyBoolean())).thenReturn(true);
    }

    @Test
    void validate_whenAllValidationPass_thenSuccess() {
        boolean result = inputsFSDValidator.validate(definition);

        assertTrue(result);
        verify(stageStore, times(1)).getById(any());
        verify(stageDefinitionStore, times(1)).getById(any());
        verify(ioTypeComparator, times(1)).areEquals(any(), any(), anyBoolean());
    }

    @Test
    void validate_whenNoRootsAndNoInputsDeclared_thenSuccess() {
        when(definition.getFunctionalSubStages()).thenReturn(Collections.emptySet());
        when(definition.getInputsByName()).thenReturn(Collections.emptyMap());

        boolean result = inputsFSDValidator.validate(definition);

        assertTrue(result);
        verify(stageStore, never()).getById(any());
        verify(stageDefinitionStore, never()).getById(any());
        verify(ioTypeComparator, never()).areEquals(any(), any(), anyBoolean());
    }

    @Test
    public void validate_whenNoInputDeclaredAndNoRootFsStage_thenSuccess() {
        InputProvider inputProvider = mock(InputProvider.class);
        when(ioDependency.getInputProviders()).thenReturn(Set.of(inputProvider));
        when(definition.getInputsByName()).thenReturn(Collections.emptyMap());

        boolean result = inputsFSDValidator.validate(definition);

        assertTrue(result);
        verify(stageStore, never()).getById(any());
        verify(stageDefinitionStore, never()).getById(any());
        verify(ioTypeComparator, never()).areEquals(any(), any(), anyBoolean());
    }

    @Test
    public void validate_whenfsInputDefinitionIsRequired_thenSuccess() {
        when(stageInputDefinition.isRequired()).thenReturn(true);
        boolean result = inputsFSDValidator.validate(definition);

        assertTrue(result);
        verify(stageStore, times(1)).getById(any());
        verify(stageDefinitionStore, times(1)).getById(any());
        verify(ioTypeComparator, times(1)).areEquals(any(), any(), anyBoolean());
    }

    @Test
    public void validate_whenStageStoreDoesNotHaveStageForStageId_throwsExpectedError() {
        when(stageStore.getById(stageId)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> inputsFSDValidator.validate(definition));

        assertEquals("Stage referenced but does not exist. " + stageId, exception.getMessage());
    }

    @Test
    public void validate_whenStageDefinitionStoreDoesNotHaveDefinitionForDefinitionId_throwsExpectedError() {
        when(stageDefinitionStore.getById(definitionId)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> inputsFSDValidator.validate(definition));

        assertEquals("stageDefinition referenced but does not exist. " + definitionId, exception.getMessage());
    }

    @Test
    public void validate_whenInputsByNameAreNotSameSizeAsRootsDefinitions_throwsExpectedError() {
        when(definition.getInputsByName()).thenReturn(Map.of(inputId, stageInputDefinition, "anotherInputId", mock(StageInputDefinition.class)));

        Exception exception = assertThrows(RuntimeException.class, () -> inputsFSDValidator.validate(definition));

        assertEquals("Expected as much inputs as roots this functional stage declares: 1", exception.getMessage());
    }

    @Test
    public void validate_whenNoInputForRootAssociatedInputName_throwsExpectedError() {
        when(definition.getInputsByName()).thenReturn(Map.of("anotherInputId", stageInputDefinition));

        Exception exception = assertThrows(RuntimeException.class, () -> inputsFSDValidator.validate(definition));

        assertEquals("Expected input name " + inputId + " in functional Stage's input definitions, but not found.", exception.getMessage());
    }


    @Test
    public void validate_whenCanBeMultiplePropertiesDifferent_throwsExpectedError() {
        when(stageInputDefinition.canBeMultiple()).thenReturn(false);
        when(subStageInputDefinition.canBeMultiple()).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> inputsFSDValidator.validate(definition));

        assertEquals("Different definition for input name " + inputId + " between FunctionalStage and its roots.", exception.getMessage());
    }

    @Test
    public void validate_whenIoTypesAreNotEqual_throwsExpectedError() {
        when(ioTypeComparator.areEquals(any(), any(), anyBoolean())).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> inputsFSDValidator.validate(definition));

        assertEquals("Different definition for input name " + inputId + " between FunctionalStage and its roots.", exception.getMessage());
    }

    @Test
    public void validate_whenOneRootHasInputRequiredButNotTheFunctionalStage_throwsExpectedError() {
        when(subStageInputDefinition.isRequired()).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> inputsFSDValidator.validate(definition));

        assertEquals("Functional Stage declares input " + inputId + " as not required, but 1 roots declares it as required input.", exception.getMessage());
    }

    @Test
    public void validate_whenfsInputDefinitionIsRequired_andCanBeMultiplePropertiesDifferent_throwsExpectedError() {
        when(stageInputDefinition.isRequired()).thenReturn(true);

        when(stageInputDefinition.canBeMultiple()).thenReturn(false);
        when(subStageInputDefinition.canBeMultiple()).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> inputsFSDValidator.validate(definition));

        assertEquals("Different definition for input name " + inputId + " between FunctionalStage and its roots.", exception.getMessage());
    }

    @Test
    public void validate_whenfsInputDefinitionIsRequired_andIoTypesAreNotEqual_throwsExpectedError() {
        when(stageInputDefinition.isRequired()).thenReturn(true);

        when(ioTypeComparator.areEquals(any(), any(), anyBoolean())).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> inputsFSDValidator.validate(definition));

        assertEquals("Different definition for input name " + inputId + " between FunctionalStage and its roots.", exception.getMessage());
    }


    private void whenHasRootsInputDefinition() {
        when(fsStage.getIoDependencies()).thenReturn(Set.of(ioDependency));
        when(ioDependency.getInputProviders()).thenReturn(Set.of()); //No inputProvider == root

        when(fsStage.getContextualizedId()).thenReturn(contextStageId);
        when(stageStore.getById(stageId)).thenReturn(subStage);
        when(subStage.getDefinitionId()).thenReturn(definitionId);
        when(stageDefinitionStore.getById(definitionId)).thenReturn(definitionOfSubStage);
        when(definitionOfSubStage.getInputsByName()).thenReturn(Map.of(inputId, subStageInputDefinition));

    }
}