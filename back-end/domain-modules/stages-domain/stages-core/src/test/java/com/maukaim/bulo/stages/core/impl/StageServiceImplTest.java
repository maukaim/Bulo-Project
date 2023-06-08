package com.maukaim.bulo.stages.core.impl;

import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.stages.core.StageValidator;
import com.maukaim.bulo.stages.core.validators.ReportStatus;
import com.maukaim.bulo.stages.core.validators.StageCreateReport;
import com.maukaim.bulo.stages.core.validators.ValidationReport;
import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StageServiceImplTest {

    private StageStore stageStore;
    private StageDefinitionService stageDefinitionService;
    private StageValidator stageValidator;
    private StageServiceImpl stageService;

    @BeforeEach
    public void setUp() {
        stageStore = mock(StageStore.class);
        stageDefinitionService = mock(StageDefinitionService.class);
        stageValidator = mock(StageValidator.class);
        stageService = new StageServiceImpl(stageStore, stageDefinitionService, stageValidator);
    }

    @Test
    public void getAllStagesTest() {
        Stage stage = mock(Stage.class);
        when(stageStore.getAll()).thenReturn(Collections.singletonList(stage));

        List<Stage> stages = stageService.getAll();

        assertEquals(1, stages.size());
        verify(stageStore, times(1)).getAll();
    }

    @Test
    public void getStageByIdTest() {
        Stage stage = mock(Stage.class);
        when(stageStore.getById(anyString())).thenReturn(stage);

        Stage foundStage = stageService.getById("test");

        assertNotNull(foundStage);
        verify(stageStore, times(1)).getById(anyString());
    }

    @Test
    public void removeStageTest() {
        Stage stage = mock(Stage.class);
        when(stageStore.getById(anyString())).thenReturn(stage);

        Stage removedStage = stageService.remove("test");

        assertNotNull(removedStage);
        verify(stageStore, times(1)).getById(anyString());
        verify(stageStore, times(1)).remove(any(Stage.class));
    }

    @Test
    public void addFunctionalStageTest() {
        FunctionalStage stage = mock(FunctionalStage.class);
        when(stage.getStageType()).thenReturn(StageType.FUNCTIONAL);

        StageCreateReport report = stageService.addStage(stage);

        assertNotNull(report);

        assertTrue(report.getDetails().contains(StageCreateReport.DEFAULT_SUCCESS_REPORT));
        verify(stageStore, times(1)).put(any(Stage.class));
    }

    @Test
    public void addTechnicalStageWithNoDefinitionIdTest() {
        TechnicalStage stage = mock(TechnicalStage.class);
        when(stage.getStageType()).thenReturn(StageType.TECHNICAL);
        when(stage.getDefinitionId()).thenReturn(null);

        StageCreateReport report = stageService.addStage(stage);

        assertNotNull(report);
        assertNotEquals(ReportStatus.SUCCESS, report.getReportStatus());
        assertTrue(report.getDetails().contains("No definition identifier provided to create the TechnicalStage. Impossible to validate."));
    }

    @Test
    public void addTechnicalStageWithNoDefinitionTest() {
        TechnicalStage stage = mock(TechnicalStage.class);
        when(stage.getStageType()).thenReturn(StageType.TECHNICAL);
        when(stage.getDefinitionId()).thenReturn("test");
        when(stageDefinitionService.getById("test")).thenReturn(null);

        StageCreateReport report = stageService.addStage(stage);

        assertNotNull(report);
        assertNotEquals(ReportStatus.SUCCESS, report.getReportStatus());
        assertTrue(report.getDetails().contains("No definition found with identifier test"));
    }

    @Test
    public void addTechnicalStageWithValidationFailureTest() {
        TechnicalStage stage = mock(TechnicalStage.class);
        StageDefinition definition = mock(StageDefinition.class);
        ValidationReport validationReport = ValidationReport.no(List.of("Validation failed"));

        when(stage.getStageType()).thenReturn(StageType.TECHNICAL);
        when(stage.getDefinitionId()).thenReturn("test");
        when(stageDefinitionService.getById("test")).thenReturn(definition);
        when(stageValidator.validate(stage, definition)).thenReturn(validationReport);

        StageCreateReport report = stageService.addStage(stage);

        assertNotNull(report);
        assertEquals(ReportStatus.ERROR, report.getReportStatus());
        assertTrue(report.getDetails().contains("Validation failed"));
    }

    @Test
    public void addTechnicalStageWithValidationSuccessTest() {
        TechnicalStage stage = mock(TechnicalStage.class);
        StageDefinition definition = mock(StageDefinition.class);

        when(stage.getStageType()).thenReturn(StageType.TECHNICAL);
        when(stage.getDefinitionId()).thenReturn("test");
        when(stageDefinitionService.getById("test")).thenReturn(definition);
        when(stageValidator.validate(stage, definition)).thenReturn(ValidationReport.DEFAULT_SUCCESS_REPORT);

        StageCreateReport report = stageService.addStage(stage);

        assertNotNull(report);
        assertEquals(ReportStatus.SUCCESS, report.getReportStatus());
        assertTrue(report.getDetails().contains(StageCreateReport.DEFAULT_SUCCESS_REPORT));
        verify(stageStore, times(1)).put(any(Stage.class));
    }

    @Test
    public void addStageWithUnknownTypeTest() {
        Stage stage = mock(Stage.class);
        when(stage.getStageType()).thenReturn(null);

        assertThrows(RuntimeException.class, () -> stageService.addStage(stage));
    }
}
