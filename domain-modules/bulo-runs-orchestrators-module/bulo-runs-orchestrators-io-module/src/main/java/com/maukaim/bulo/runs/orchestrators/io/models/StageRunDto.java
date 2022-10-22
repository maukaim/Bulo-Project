package com.maukaim.bulo.runs.orchestrators.io.models;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class StageRunDto {
    private final String stageRunId;
    private final ContextualizedStageId contextualizedStageId;
    private final String flowRunId;
    private final StageRunStatusDto stageRunStatus;
    private final String executorId;
    private final Set<StageRunDependencyDto> dependencies;
    private final Instant startTime;
    private final Instant endTime;

    public StageRunDto(String stageRunId, ContextualizedStageId contextualizedStageId, String flowRunId, StageRunStatusDto stageRunStatus, String executorId, Set<StageRunDependencyDto> dependencies, Instant startTime, Instant endTime) {
        this.stageRunId = stageRunId;
        this.contextualizedStageId = contextualizedStageId;
        this.flowRunId = flowRunId;
        this.stageRunStatus = stageRunStatus;
        this.executorId = executorId;
        this.dependencies = dependencies;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public ContextualizedStageId getFlowStageId() {
        return contextualizedStageId;
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
        return dependencies;
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
                ", flowStageId=" + contextualizedStageId +
                ", flowRunId='" + flowRunId + '\'' +
                ", stageRunStatus=" + stageRunStatus +
                ", executorId='" + executorId + '\'' +
                ", stageRunDependencyDto=" + dependencies +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}