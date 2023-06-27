package com.maukaim.bulo.definitions.registry.core.report;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StageDefinitionCreateReportTest {

    @Test
    public void failReport_whenSingleReason_shouldCreateErrorReport() {
        String definitionId = "def123";
        String reason = "Invalid input";
        StageDefinitionCreateReport report = StageDefinitionCreateReport.failReport(definitionId, reason);

        assertEquals(definitionId, report.getDefinitionId());
        assertEquals(List.of(reason), report.getDetails());
        assertEquals(ReportStatus.ERROR, report.getReportStatus());
    }

    @Test
    public void failReport_whenMultipleReasons_shouldCreateErrorReport() {
        String definitionId = "def123";
        List<String> reasons = Arrays.asList("Invalid input", "Null value detected");
        StageDefinitionCreateReport report = StageDefinitionCreateReport.failReport(definitionId, reasons);

        assertEquals(definitionId, report.getDefinitionId());
        assertEquals(reasons, report.getDetails());
        assertEquals(ReportStatus.ERROR, report.getReportStatus());
    }

    // Edge Case: What if the reason list or message is empty?
    @Test
    public void failReport_whenNoReasons_shouldStillCreateErrorReport() {
        String definitionId = "def123";
        List<String> reasons = List.of();
        StageDefinitionCreateReport report = StageDefinitionCreateReport.failReport(definitionId, reasons);

        assertEquals(definitionId, report.getDefinitionId());
        assertTrue(report.getDetails().isEmpty());
        assertEquals(ReportStatus.ERROR, report.getReportStatus());
    }

    @Test
    public void failReport_whenReasonIsEmpty_shouldStillCreateErrorReport() {
        String definitionId = "def123";
        String reason = "";
        StageDefinitionCreateReport report = StageDefinitionCreateReport.failReport(definitionId, reason);

        assertEquals(definitionId, report.getDefinitionId());
        assertEquals(List.of("No reason provided."), report.getDetails());
        assertEquals(ReportStatus.ERROR, report.getReportStatus());
    }

    @Test
    public void successReport_whenCalled_shouldCreateSuccessReport() {
        String definitionId = "def123";
        String msg = "Success";
        StageDefinitionCreateReport report = StageDefinitionCreateReport.successReport(definitionId, msg);

        assertEquals(definitionId, report.getDefinitionId());
        assertEquals(List.of(msg), report.getDetails());
        assertEquals(ReportStatus.SUCCESS, report.getReportStatus());
    }

    @Test
    public void successReport_whenNoMessage_shouldStillCreateSuccessReport() {
        String definitionId = "def123";
        String msg = "";
        StageDefinitionCreateReport report = StageDefinitionCreateReport.successReport(definitionId, msg);

        assertEquals(definitionId, report.getDefinitionId());
        assertTrue(report.getDetails().isEmpty());
        assertEquals(ReportStatus.SUCCESS, report.getReportStatus());
    }

    @Test
    public void successReport_whenMessageIsNull_shouldStillCreateSuccessReport() {
        String definitionId = "def123";
        StageDefinitionCreateReport report = StageDefinitionCreateReport.successReport(definitionId, null);

        assertEquals(definitionId, report.getDefinitionId());
        assertTrue(report.getDetails().isEmpty());
        assertEquals(ReportStatus.SUCCESS, report.getReportStatus());
    }
}