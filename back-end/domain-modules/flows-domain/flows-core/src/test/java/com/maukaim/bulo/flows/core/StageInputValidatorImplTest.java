package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.definition.StageInputDefinition;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StageInputValidatorImplTest {
    private final IoDependency ioDependency = mock(IoDependency.class);
    private final String inputId = "inputId";
    private final String definitionId = "definitionId";
    private final StageDefinition stageDefinition = mock(StageDefinition.class);
    private final StageInputDefinition stageInputDefinition = mock(StageInputDefinition.class);
    private StageInputValidator stageInputValidator;

    @BeforeEach
    void init() {
        when(ioDependency.getInputId()).thenReturn(inputId);
        when(stageDefinition.getInputsByName()).thenReturn(Map.of(inputId, stageInputDefinition));
        when(stageInputDefinition.isRequired()).thenReturn(false);
        when(stageDefinition.getStageDefinitionId()).thenReturn(definitionId);
        stageInputValidator = new StageInputValidatorImpl();
    }

    @Test
    void validate_success() throws FlowValidationException {
        assertDoesNotThrow(() -> stageInputValidator.validate(Set.of(ioDependency), stageDefinition));
    }

    @Test
    void validate_whenIoDependencyInInputMap_andDefIsRequired_thenStillSuccess() {
        when(stageInputDefinition.isRequired()).thenReturn(true);
        assertDoesNotThrow(() -> stageInputValidator.validate(Set.of(ioDependency), stageDefinition));
    }

    @Test
    void validate_whenIoDependencyNotInInputMap_andDefIsNotRequired_thenStillSuccess() {
        when(stageDefinition.getInputsByName()).thenReturn(
                Map.of(inputId, stageInputDefinition,
                        "notSameInputId", stageInputDefinition)
        );
        assertDoesNotThrow(() -> stageInputValidator.validate(Set.of(ioDependency), stageDefinition));
    }

    @Test
    void validate_whenIoDependencyNotInInputMap_andDefIsRequired_thenThrows() {
        String notSameInputId = "notSameInputId";
        when(stageInputDefinition.isRequired()).thenReturn(true);
        when(stageDefinition.getInputsByName()).thenReturn(
                Map.of(inputId, stageInputDefinition,
                        notSameInputId, stageInputDefinition)
        );
        FlowValidationException exception = assertThrows(FlowValidationException.class,
                () -> stageInputValidator.validate(Set.of(ioDependency), stageDefinition));
        assertEquals("Input " + notSameInputId + " not provided but required by definition " + definitionId + ". Invalid flow.", exception.getMessage());
    }

    @Test
    void validate_whenIoDependenciesHasInputIdNotInDefInputMap_thenThrows() {
        String notSameInputId = "notSameInputId";
        IoDependency ioDependency2 = mock(IoDependency.class);
        when(ioDependency2.getInputId()).thenReturn(notSameInputId);

        FlowValidationException exception = assertThrows(FlowValidationException.class,
                () -> stageInputValidator.validate(Set.of(ioDependency, ioDependency2), stageDefinition));
        assertEquals("Input " + notSameInputId + " provided to stage but not present in stage definition " + definitionId + ". Invalid flow.", exception.getMessage());
    }

}

