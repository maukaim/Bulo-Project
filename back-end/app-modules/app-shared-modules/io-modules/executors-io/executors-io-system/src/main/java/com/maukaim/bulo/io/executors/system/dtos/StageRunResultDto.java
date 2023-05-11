package com.maukaim.bulo.io.executors.system.dtos;

import java.util.Map;

public class StageRunResultDto {
    private final String stageRunId;
    private final StageRunStatusDto status;
    private final Map<String, String> outputsByName;

    public StageRunResultDto(String stageRunId, StageRunStatusDto status, Map<String, String> outputsByName) {
        this.stageRunId = stageRunId;
        this.status = status;
        this.outputsByName = outputsByName;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public StageRunStatusDto getStatus() {
        return status;
    }

    public Map<String, String> getOutputsByName() {
        return outputsByName;
    }

    @Override
    public String toString() {
        return "StageRunResultDto{" +
                "stageRunId='" + stageRunId + '\'' +
                ", status=" + status +
                ", outputsByName=" + outputsByName +
                '}';
    }
}
