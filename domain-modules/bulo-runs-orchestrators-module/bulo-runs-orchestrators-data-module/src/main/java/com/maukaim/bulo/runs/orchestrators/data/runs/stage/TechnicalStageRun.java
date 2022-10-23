package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.commons.models.StageType;

import java.time.Instant;
import java.util.Set;

public class TechnicalStageRun implements StageRun<TechnicalStageRunStatus> {
    private final String stageRunId;
    private final ContextualizedStageId contextualizedStageId;
    private final Context<?> context;
    private final TechnicalStageRunStatus stageRunStatus;
    private final String executorId;
    private final Set<StageRunDependency> stageRunDependencies;
    private final Instant startTime;
    private final Instant endTime;

    public TechnicalStageRun(String stageRunId,
                             ContextualizedStageId contextualizedStageId,
                             Context<?> context,
                             TechnicalStageRunStatus stageRunStatus,
                             String executorId, Set<StageRunDependency> stageRunDependencies, Instant startTime, Instant endTime) {
        this.stageRunId = stageRunId;
        this.contextualizedStageId = contextualizedStageId;
        this.context = context;
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
    public ContextualizedStageId getContextualizedStageId() {
        return contextualizedStageId;
    }

    @Override
    public Context<?> getContext() {
        return context;
    }

    @Override
    public TechnicalStageRunStatus getStatus() {
        return stageRunStatus;
    }

    public String getExecutorId() {
        return executorId;
    }

    @Override
    public Set<StageRunDependency> getStageRunDependencies() {
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
                ", contextualizedStageId=" + contextualizedStageId +
                ", context=" + context +
                ", stageRunStatus=" + stageRunStatus +
                ", executorId='" + executorId + '\'' +
                ", stageRunDependencies=" + stageRunDependencies +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
