package com.maukaim.bulo.definitions.registry.core.report;

import java.util.List;

public class StageDefinitionCreateReport {
    public static final String DEFAULT_SUCCESS_REPORT = "Stage Definition validation successfully passed.";
    private List<String> details;
    private ReportStatus reportStatus;
    private String definitionId;

    public static StageDefinitionCreateReport failReport(String stageId, String reason) {
        return failReport(stageId, List.of(reason));
    }

    public static StageDefinitionCreateReport failReport(String stageId, List<String> reasons) {
        return new StageDefinitionCreateReport(ReportStatus.ERROR, stageId, reasons);
    }

    public static StageDefinitionCreateReport successReport(String stageId, String msg) {
        return new StageDefinitionCreateReport(ReportStatus.SUCCESS, stageId, List.of(msg));
    }

    public StageDefinitionCreateReport(ReportStatus reportStatus, String definitionId, List<String> details) {
        this.definitionId = definitionId;
        this.details = details;
        this.reportStatus = reportStatus;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public List<String> getDetails() {
        return details;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    @Override
    public String toString() {
        return "StageDefinitionCreateReport{" +
                "details=" + details +
                ", reportStatus=" + reportStatus +
                ", definitionId='" + definitionId + '\'' +
                '}';
    }
}
