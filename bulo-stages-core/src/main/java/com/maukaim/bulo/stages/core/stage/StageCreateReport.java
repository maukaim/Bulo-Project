package com.maukaim.bulo.stages.core.stage;

import java.util.List;

public class StageCreateReport {
    public static final StageCreateReport DEFAULT_SUCCESS_REPORT = successReport("Stage validation successfully passed.");
    private List<String> details;
    private ReportStatus reportStatus;

    public static StageCreateReport failReport(String reason) {
        return failReport(List.of(reason));
    }

    public static StageCreateReport failReport(List<String> reasons) {
        return new StageCreateReport(ReportStatus.ERROR, reasons);
    }

    public static StageCreateReport successReport(String msg) {
        return new StageCreateReport(ReportStatus.SUCCESS, List.of(msg));
    }

    public StageCreateReport(ReportStatus reportStatus, List<String> details) {
        this.details = details;
        this.reportStatus = reportStatus;
    }

    public List<String> getDetails() {
        return details;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }
}
