package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.commons.models.StageType;

import java.time.Instant;
import java.util.Set;

public class TechnicalStageRun implements StageRun<TechnicalStageRunStatus> {
    private final String stageRunId;
    private final ContextStageId contextStageId;
    private final RunContext<?> runContext;
    private final TechnicalStageRunStatus stageRunStatus;
    private final String executorId;
    private final Set<RunDependency> stageRunDependencies;
    private final Instant startTime;
    private final Instant endTime;

    public TechnicalStageRun(String stageRunId,
                             ContextStageId contextStageId,
                             RunContext<?> runContext,
                             TechnicalStageRunStatus stageRunStatus,
                             String executorId, Set<RunDependency> stageRunDependencies, Instant startTime, Instant endTime) {
        this.stageRunId = stageRunId;
        this.contextStageId = contextStageId;
        this.runContext = runContext;
        this.stageRunStatus = stageRunStatus;
        this.executorId = executorId;
        this.stageRunDependencies = stageRunDependencies;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public StageType getStageType() {
        return StageType.TECHNICAL;
    }

    @Override
    public String getStageRunId() {
        return stageRunId;
    }

    @Override
    public ContextStageId getContextualizedStageId() {
        return contextStageId;
    }

    @Override
    public RunContext<?> getContext() {
        return runContext;
    }

    @Override
    public TechnicalStageRunStatus getStatus() {
        return stageRunStatus;
    }

    public String getExecutorId() {
        return executorId;
    }

    @Override
    public Set<RunDependency> getStageRunDependencies() {
        return stageRunDependencies;
    }

    @Override
    public Instant getEndTime() {
        return endTime;
    }

    @Override
    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "TechnicalStageRun{" +
                "stageRunId='" + stageRunId + '\'' +
                ", contextualizedStageId=" + contextStageId +
                ", context=" + runContext +
                ", stageRunStatus=" + stageRunStatus +
                ", executorId='" + executorId + '\'' +
                ", stageRunDependencies=" + stageRunDependencies +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
