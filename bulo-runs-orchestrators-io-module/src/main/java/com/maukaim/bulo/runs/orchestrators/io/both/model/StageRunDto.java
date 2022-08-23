package com.maukaim.bulo.runs.orchestrators.io.both.model;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.time.Instant;

public class StageRunDto {
    private final String stageRunId;
    private final FlowStageId flowStageId;
    private final String flowRunId;
    private final StageRunStatusDto stageRunStatus;
    private final String executorId;
    private final Instant startTime;
    private final Instant endTime;

    public StageRunDto(String stageRunId, FlowStageId flowStageId, String flowRunId, StageRunStatusDto stageRunStatus, String executorId, Instant startTime, Instant endTime) {
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

    public StageRunStatusDto getStageRunStatus() {
        return stageRunStatus;
    }

    public String getExecutorId() {
        return executorId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
}