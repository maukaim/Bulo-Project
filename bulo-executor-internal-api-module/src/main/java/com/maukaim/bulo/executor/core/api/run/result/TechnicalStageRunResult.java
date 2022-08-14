package com.maukaim.bulo.executor.core.api.run.result;

import java.util.Map;

public class TechnicalStageRunResult {
    private String stageRunId;
    private StageRunStatus status;
    private Map<String, String> outputsByName;


    public TechnicalStageRunResult(String stageRunId, StageRunStatus status, Map<String, String> outputsByName) {
        this.stageRunId = stageRunId;
        this.status = status;
        this.outputsByName = outputsByName;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public StageRunStatus getStatus() {
        return status;
    }

    public Map<String, String> getOutputs() {
        return outputsByName;
    }

    @Override
    public String toString() {
        return "TechnicalStageRunResult{" +
                "stageRunId='" + stageRunId + '\'' +
                ", status=" + status +
                ", outputsByName=" + outputsByName +
                '}';
    }
}
