package com.maukaim.bulo.runs.orchestrators.io.models;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.ContextDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class TechnicalStageRunDto implements StageRunDto<StageRunStatusDto> {
    protected final String stageRunId;
    protected final ContextualizedStageId contextualizedStageId;
    protected final ContextDto<?> context;
    protected final StageRunStatusDto stageRunStatus;
    protected final String executorId;
    protected final Set<StageRunDependencyDto> dependencies;
    protected final Instant startTime;
    protected final Instant endTime;

    public TechnicalStageRunDto(String stageRunId,
                                ContextualizedStageId contextualizedStageId,
                                ContextDto<?> context,
                                StageRunStatusDto stageRunStatus,
                                String executorId,
                                Set<StageRunDependencyDto> dependencies,
                                Instant startTime,
                                Instant endTime) {
        this.stageRunId = stageRunId;
        this.contextualizedStageId = contextualizedStageId;
        this.context = context;
        this.stageRunStatus = stageRunStatus;
        this.executorId = executorId;
        this.dependencies = dependencies;
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
    public ContextDto getContext() {
        return context;
    }

    @Override
    public StageRunStatusDto getStatus() {
        return stageRunStatus;
    }

    public String getExecutorId() {
        return executorId;
    }

    @Override
    public Set<StageRunDependencyDto> getDependencies() {
        return dependencies;
    }

    @Override
    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public Instant getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "TechnicalStageRunDto{" +
                "stageRunId='" + stageRunId + '\'' +
                ", contextualizedStageId=" + contextualizedStageId +
                ", context=" + context +
                ", stageRunStatus=" + stageRunStatus +
                ", executorId='" + executorId + '\'' +
                ", dependencies=" + dependencies +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}