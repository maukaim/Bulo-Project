package com.maukaim.bulo.runs.orchestrators.io.models;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.ContextDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class TechnicalStageRunDto implements StageRunDto<StageRunStatusDto> {
    protected final String stageRunId;
    protected final ContextStageId contextStageId;
    protected final ContextDto<?> context;
    protected final StageRunStatusDto stageRunStatus;
    protected final String executorId;
    protected final Set<StageRunDependencyDto> dependencies;
    protected final Instant startTime;
    protected final Instant endTime;

    public TechnicalStageRunDto(String stageRunId,
                                ContextStageId contextStageId,
                                ContextDto<?> context,
                                StageRunStatusDto stageRunStatus,
                                String executorId,
                                Set<StageRunDependencyDto> dependencies,
                                Instant startTime,
                                Instant endTime) {
        this.stageRunId = stageRunId;
        this.contextStageId = contextStageId;
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
    public ContextStageId getContextualizedStageId() {
        return contextStageId;
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
                ", contextualizedStageId=" + contextStageId +
                ", context=" + context +
                ", stageRunStatus=" + stageRunStatus +
                ", executorId='" + executorId + '\'' +
                ", dependencies=" + dependencies +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}