package com.maukaim.bulo.stages.core.validators;

import java.util.List;

public class StageCreateReport {
    public static final String DEFAULT_SUCCESS_REPORT = "Stage validation successfully passed.";
    private final List<String> details;
    private final ReportStatus reportStatus;
    private final String stageId;

    public static StageCreateReport failReport(String stageId, String reason) {
        return failReport(stageId, List.of(reason));
    }

    public static StageCreateReport failReport(String stageId, List<String> reasons) {
        return new StageCreateReport(ReportStatus.ERROR, stageId, reasons);
    }

    public static StageCreateReport successReport(String stageId, String msg) {
        return new StageCreateReport(ReportStatus.SUCCESS, stageId, List.of(msg));
    }

    public StageCreateReport(ReportStatus reportStatus, String stageId, List<String> details) {
        this.stageId = stageId;
        this.details = details;
        this.reportStatus = reportStatus;
    }

    public String getStageId() {
        return stageId;
    }

    public List<String> getDetails() {
        return details;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    @Override
    public String toString() {
        return "StageCreateReport{" +
                "details=" + details +
                ", reportStatus=" + reportStatus +
                ", stageId='" + stageId + '\'' +
                '}';
    }
}
