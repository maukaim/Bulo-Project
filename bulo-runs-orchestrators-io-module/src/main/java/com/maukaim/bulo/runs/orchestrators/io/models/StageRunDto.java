package com.maukaim.bulo.runs.orchestrators.io.models;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class StageRunDto {
    private final String stageRunId;
    private final FlowStageId flowStageId;
    private final String flowRunId;
    private final StageRunStatusDto stageRunStatus;
    private final String executorId;
    private final Set<StageRunDependencyDto> stageRunDependencyDto;
    private final Instant startTime;
    private final Instant endTime;

    public StageRunDto(String stageRunId, FlowStageId flowStageId, String flowRunId, StageRunStatusDto stageRunStatus, String executorId, Set<StageRunDependencyDto> stageRunDependencyDto, Instant startTime, Instant endTime) {
        this.stageRunId = stageRunId;
        this.flowStageId = flowStageId;
        this.flowRunId = flowRunId;
        this.stageRunStatus = stageRunStatus;
        this.executorId = executorId;
        this.stageRunDependencyDto = stageRunDependencyDto;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public FlowStageId getFlowStageId() {
        return flowStageId;
    }

    public String getFlowRunId() {
        return flowRunId;
    }

    public StageRunStatusDto getStageRunStatus() {
        return stageRunStatus;
    }

    public String getExecutorId() {
        return executorId;
    }

    public Set<StageRunDependencyDto> getDependencies() {
        return stageRunDependencyDto;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "StageRunDto{" +
                "stageRunId='" + stageRunId + '\'' +
                ", flowStageId=" + flowStageId +
                ", flowRunId='" + flowRunId + '\'' +
                ", stageRunStatus=" + stageRunStatus +
                ", executorId='" + executorId + '\'' +
                ", stageRunDependencyDto=" + stageRunDependencyDto +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}