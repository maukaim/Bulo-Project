package com.maukaim.bulo.runs.orchestrator.core.stagerun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maukaim.bulo.commons.core.models.FlowStageId;

import java.time.Instant;

public class StageRun {
    private final String stageRunId;
    private final FlowStageId flowStageId;
    private final String flowRunId;
    private final StageRunStatus stageRunStatus;
    private final String executorId;
    private final Instant startTime;
    private final Instant endTime;

    public StageRun(String stageRunId, FlowStageId flowStageId, String flowRunId, StageRunStatus stageRunStatus, String executorId, Instant startTime, Instant endTime) {
        this.stageRunId = stageRunId;
        this.flowStageId = flowStageId;
        this.flowRunId = flowRunId;
        this.stageRunStatus = stageRunStatus;
        this.executorId = executorId;
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

    public StageRunStatus getStageRunStatus() {
        return stageRunStatus;
    }

    public String getExecutorId() {
        return executorId;
    }

    @JsonIgnore
    public boolean isTerminated() {
        return this.getStageRunStatus() != null && this.getStageRunStatus().isTerminal();
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "StageRunView{" +
                "stageRunId='" + stageRunId + '\'' +
                ", flowStageId=" + flowStageId +
                ", flowRunId='" + flowRunId + '\'' +
                ", stageRunStatus=" + stageRunStatus +
                ", executorId='" + executorId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
