package com.maukaim.bulo.runs.orchestrator.core.stagerun.model;

import com.maukaim.bulo.common.utils.TimeHelper;
import com.maukaim.bulo.flows.api.FlowStageId;

import java.time.Instant;
import java.util.UUID;

public class StageRunFactory {

    public static StageRun requested(String flowRunId, FlowStageId stageId) {
        return new StageRun(UUID.randomUUID().toString(), stageId, flowRunId, StageRunStatus.REQUESTED, null, Instant.now(), null);
    }

    public static StageRun acknowledged(StageRun previousView, String executorId) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.ACKNOWLEDGED.resolveComparedTo(previousView.getStageRunStatus()), executorId,
                previousView.getStartTime(),
                previousView.getEndTime());
    }

    public static StageRun launched(StageRun previousView, Instant startTime) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.RUNNING.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                startTime,
                previousView.getEndTime());
    }

    public static StageRun failed(StageRun previousView, Instant endTime) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.FAILED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }

    public static StageRun cancelled(StageRun previousView, Instant endTime) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.CANCELLED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }

    public static StageRun success(StageRun previousView, Instant endTime) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.SUCCESS.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }
}
