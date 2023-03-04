package com.maukaim.bulo.executors.data.result;

import java.util.Map;

public class StageRunResult {
    private final String stageRunId;
    private final StageRunStatus status;
    private final Map<String, String> outputsByName;

    public static StageRunResult of(String stageRunId, StageRunStatus status){
        return new StageRunResult(stageRunId, status, Map.of());
    }
    public StageRunResult(String stageRunId, StageRunStatus status, Map<String, String> outputsByName) {
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
