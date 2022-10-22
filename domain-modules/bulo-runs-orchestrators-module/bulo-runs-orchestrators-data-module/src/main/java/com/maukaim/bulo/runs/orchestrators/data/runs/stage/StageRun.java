package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.time.Instant;
import java.util.Set;

public class StageRun {
    private final String stageRunId;
    private final ContextualizedStageId contextualizedStageId;
    private final String flowRunId;
    private final StageRunStatus stageRunStatus;
    private final String executorId;
    private final Set<StageRunDependency> stageRunDependencies;
    private final Instant startTime;
    private final Instant endTime;

    public StageRun(String stageRunId, ContextualizedStageId contextualizedStageId, String flowRunId, StageRunStatus stageRunStatus, String executorId, Set<StageRunDependency> stageRunDependencies, Instant startTime, Instant endTime) {
        this.stageRunId = stageRunId;
        this.contextualizedStageId = contextualizedStageId;
        this.flowRunId = flowRunId;
        this.stageRunStatus = stageRunStatus;
        this.executorId = executorId;
        this.stageRunDependencies = stageRunDependencies;
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

    public StageRunStatus getStageRunStatus() {
        return stageRunStatus;
    }

    public String getExecutorId() {
        return executorId;
    }

    public Set<StageRunDependency> getStageRunDependencies() {
        return stageRunDependencies;
    }

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
                ", flowStageId=" + contextualizedStageId +
                ", flowRunId='" + flowRunId + '\'' +
                ", stageRunStatus=" + stageRunStatus +
                ", executorId='" + executorId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
