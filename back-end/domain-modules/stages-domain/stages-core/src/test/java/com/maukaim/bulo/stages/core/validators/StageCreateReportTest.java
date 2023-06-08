package com.maukaim.bulo.stages.core.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class StageCreateReportTest {

    @Test
    public void failReport_whenSingleReason_shouldReturnErrorReport() {
        String stageId = "stage1";
        String reason = "Failure reason";

        StageCreateReport report = StageCreateReport.failReport(stageId, reason);

        assertEquals(ReportStatus.ERROR, report.getReportStatus());
        assertEquals(stageId, report.getStageId());
        assertTrue(report.getDetails().contains(reason));
    }

    @Test
    public void failReport_whenMultipleReasons_shouldReturnErrorReport() {
        String stageId = "stage1";
        List<String> reasons = Arrays.asList("Failure reason 1", "Failure reason 2");

        StageCreateReport report = StageCreateReport.failReport(stageId, reasons);

        assertEquals(ReportStatus.ERROR, report.getReportStatus());
        assertEquals(stageId, report.getStageId());
        assertTrue(report.getDetails().containsAll(reasons));
    }

    @Test
    public void successReport_whenMessage_shouldReturnSuccessReport() {
        String stageId = "stage1";
        String message = "Success message";

        StageCreateReport report = StageCreateReport.successReport(stageId, message);

        assertEquals(ReportStatus.SUCCESS, report.getReportStatus());
        assertEquals(stageId, report.getStageId());
        assertTrue(report.getDetails().contains(message));
    }

    @Test
    public void constructor_whenGivenReportStatusAndDetails_shouldCreateReport() {
        String stageId = "stage1";
        ReportStatus status = ReportStatus.SUCCESS;
        List<String> details = Arrays.asList("Detail 1", "Detail 2");

        StageCreateReport report = new StageCreateReport(status, stageId, details);

        assertEquals(status, report.getReportStatus());
        assertEquals(stageId, report.getStageId());
        assertTrue(report.getDetails().containsAll(details));
    }

    @Test
    public void toString_whenCalled_shouldReturnStringRepresentation() {
        String stageId = "stage1";
        ReportStatus status = ReportStatus.SUCCESS;
        List<String> details = Arrays.asList("Detail 1", "Detail 2");

        StageCreateReport report = new StageCreateReport(status, stageId, details);

        String expectedString = "StageCreateReport{details=" + details +
                ", reportStatus=" + status +
                ", stageId='" + stageId + "'}";

        assertEquals(expectedString, report.toString());
    }
}