package com.maukaim.bulo.definitions.registry.core.validators;

import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FsStagesFSDValidatorTest {
    private StageStore stageStore;
    private AcyclicValidator<ContextStageId> acyclicValidator;
    private FsStagesFSDValidator fsStagesFSDValidator;

    private final String stageId = "stageId";
    private final FunctionalStageDefinition definition = mock(FunctionalStageDefinition.class);
    private final FsStage fsStage = mock(FsStage.class);
    private final ContextStageId contextStageId = ContextStageId.of(stageId);

    @BeforeEach
    void init() {
        this.stageStore = mock(StageStore.class);
        this.acyclicValidator = mock(AcyclicValidator.class);
        this.fsStagesFSDValidator = new FsStagesFSDValidator(stageStore, acyclicValidator);

        when(definition.getFunctionalSubStages()).thenReturn(Set.of(fsStage));
        when(fsStage.getContextualizedId()).thenReturn(contextStageId);
        when(stageStore.contains(stageId)).thenReturn(true);
    }

    @Test
    void validate_whenAllPass_thenSuccess() {
        boolean result = fsStagesFSDValidator.validate(definition);

        assertTrue(result);
        verify(acyclicValidator).validate(any());
        verify(stageStore).contains(stageId);
    }

    @Test
    void validate_whenFunctionalSubStagesIsNull_thenThrowsException() {
        when(definition.getFunctionalSubStages()).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> fsStagesFSDValidator.validate(definition));
        assertEquals("SubStages can't be null. It would mean the FunctionalStage does nothing.", exception.getMessage());

    }

    @Test
    void validate_whenFunctionalSubStagesIsEmpty_thenThrowsException() {
        when(definition.getFunctionalSubStages()).thenReturn(Set.of());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> fsStagesFSDValidator.validate(definition));
        assertEquals("SubStages can't be null. It would mean the FunctionalStage does nothing.", exception.getMessage());
    }

    @Test
    void validate_whenStageNotKnown_thenThrowsException() {
        when(stageStore.contains(stageId)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> fsStagesFSDValidator.validate(definition));
        assertEquals("Stage with following Id not known: " + stageId, exception.getMessage());
    }

    @Test
    void validate_whenAcyclicValidatorThrowsException_thenThrowsException() {
        doThrow(new RuntimeException("AcyclicValidator message")).when(acyclicValidator).validate(any());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> fsStagesFSDValidator.validate(definition));
        assertEquals("AcyclicValidator message", exception.getMessage());
    }
}