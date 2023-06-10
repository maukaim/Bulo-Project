package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.report.ReportStatus;
import com.maukaim.bulo.definitions.registry.core.report.StageDefinitionCreateReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StageDefinitionServiceImplTest {
    private StageDefinitionServiceImpl stageDefinitionServiceImpl;
    private StageDefinitionStore mockDefinitionStore;
    private List<TechnicalStageDefinitionValidator> mockTechnicalValidators;
    private List<FunctionalStageDefinitionValidator> mockFunctionalValidators;

    @BeforeEach
    public void setUp() {
        mockDefinitionStore = mock(StageDefinitionStore.class);
        mockTechnicalValidators = Arrays.asList(mock(TechnicalStageDefinitionValidator.class), mock(TechnicalStageDefinitionValidator.class));
        mockFunctionalValidators = Arrays.asList(mock(FunctionalStageDefinitionValidator.class), mock(FunctionalStageDefinitionValidator.class));

        stageDefinitionServiceImpl = new StageDefinitionServiceImpl(mockDefinitionStore, mockTechnicalValidators, mockFunctionalValidators);
    }


    @Test
    public void registerFunctional_whenDefinitionDoesNotExistAndValidatorsPass_thenSuccess() {
        FunctionalStageDefinition definition = mock(FunctionalStageDefinition.class);
        when(definition.getDefinitionId()).thenReturn("definitionId");
        when(mockDefinitionStore.getById("definitionId")).thenReturn(null);
        for (FunctionalStageDefinitionValidator validator : mockFunctionalValidators) {
            when(validator.validate(definition)).thenReturn(true);
        }
        when(mockDefinitionStore.addDefinition(any())).thenReturn(definition);

        StageDefinitionCreateReport result = stageDefinitionServiceImpl.register(definition);

        assertEquals(ReportStatus.SUCCESS, result.getReportStatus());
        verify(mockDefinitionStore).addDefinition(any());
    }

    @Test
    public void registerFunctional_whenDefinitionDoesNotExistAndValidatorsFail_thenFail() {
        FunctionalStageDefinition definition = mock(FunctionalStageDefinition.class);
        when(definition.getDefinitionId()).thenReturn("definitionId");
        when(mockDefinitionStore.getById("definitionId")).thenReturn(null);
        for (FunctionalStageDefinitionValidator validator : mockFunctionalValidators) {
            when(validator.validate(definition)).thenReturn(false);
        }

        StageDefinitionCreateReport result = stageDefinitionServiceImpl.register(definition);

        assertEquals(ReportStatus.ERROR, result.getReportStatus());
        assertTrue(result.getDetails().contains("Definition rejected, did not pass ALL validators."));

    }

    @Test
    public void registerFunctional_whenDefinitionExists_thenFail() {
        FunctionalStageDefinition definition = mock(FunctionalStageDefinition.class);
        when(definition.getDefinitionId()).thenReturn("definitionId");
        when(mockDefinitionStore.getById("definitionId")).thenReturn(mock(StageDefinition.class));

        StageDefinitionCreateReport result = stageDefinitionServiceImpl.register(definition);

        assertEquals(ReportStatus.ERROR, result.getReportStatus());
        assertTrue(result.getDetails().contains("Definition already exist."));
    }

    @Test
    public void registerTechnical_whenDefinitionExistsAndSame_thenExecutorIdRegistered() {
        String stageExecutorId = "stageExecutorId";
        TechnicalStageDefinition definition = mock(TechnicalStageDefinition.class);
        when(definition.getDefinitionId()).thenReturn("definitionId");
        when(mockDefinitionStore.getById("definitionId")).thenReturn(definition);

        StageDefinitionCreateReport result = stageDefinitionServiceImpl.register(stageExecutorId, definition);

        assertEquals(ReportStatus.SUCCESS, result.getReportStatus());
        assertTrue(result.getDetails().contains("Already exist. just registered executorId."));
        verify(mockDefinitionStore).addExecutor(stageExecutorId, "definitionId");
    }

    @Test
    public void registerTechnical_whenDefinitionDoesNotExistAndValidatorsPass_thenDefinitionAddedAndExecutorIdRegistered() {
        String stageExecutorId = "stageExecutorId";
        TechnicalStageDefinition definition = mock(TechnicalStageDefinition.class);
        when(definition.getDefinitionId()).thenReturn("definitionId");
        when(mockDefinitionStore.getById("definitionId")).thenReturn(null);
        for (TechnicalStageDefinitionValidator validator : mockTechnicalValidators) {
            when(validator.validate(definition)).thenReturn(true);
        }

        StageDefinitionCreateReport result = stageDefinitionServiceImpl.register(stageExecutorId, definition);

        assertEquals(ReportStatus.SUCCESS, result.getReportStatus());
        assertTrue(result.getDetails().contains(StageDefinitionCreateReport.DEFAULT_SUCCESS_REPORT));
        verify(mockDefinitionStore).addDefinition(definition);
        verify(mockDefinitionStore).addExecutor(stageExecutorId, "definitionId");
    }

    @Test
    public void registerTechnical_whenDefinitionDoesNotExistAndValidatorsFail_thenDefinitionRejected() {
        String stageExecutorId = "stageExecutorId";
        TechnicalStageDefinition definition = mock(TechnicalStageDefinition.class);
        when(definition.getDefinitionId()).thenReturn("definitionId");
        when(mockDefinitionStore.getById("definitionId")).thenReturn(null);
        for (TechnicalStageDefinitionValidator validator : mockTechnicalValidators) {
            when(validator.validate(definition)).thenReturn(false);
        }

        StageDefinitionCreateReport result = stageDefinitionServiceImpl.register(stageExecutorId, definition);

        assertEquals(ReportStatus.ERROR, result.getReportStatus());
        assertTrue(result.getDetails().contains("Definition rejected, did not pass ALL validators."));
        verify(mockDefinitionStore, never()).addDefinition(any());
        verify(mockDefinitionStore, never()).addExecutor(any(), any());
    }

    @Test
    public void unregister_whenCalled_thenExecutorRemoved() {
        String stageExecutorId = "stageExecutorId";
        String definitionId = "definitionId";

        stageDefinitionServiceImpl.unregister(stageExecutorId, definitionId);

        verify(mockDefinitionStore).removeExecutor(stageExecutorId, definitionId);
    }

    @Test
    public void getAll_whenCalled_thenAllDefinitionsReturned() {
        List<StageDefinition> mockDefinitions = Arrays.asList(mock(StageDefinition.class), mock(StageDefinition.class));
        when(mockDefinitionStore.getAll()).thenReturn(mockDefinitions);

        List<StageDefinition> result = stageDefinitionServiceImpl.getAll();

        assertEquals(mockDefinitions, result);
        verify(mockDefinitionStore).getAll();
    }
}
