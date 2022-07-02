package com.maukaim.bulo.runs.orchestrator.stage.run.model;

import com.maukaim.bulo.common.utils.TimeHelper;
import com.maukaim.bulo.runs.orchestrator.flow.view.FlowStageId;

import java.time.Instant;
import java.util.UUID;

public class StageRunViewFactory {

    public static StageRunView requested(String flowRunId, FlowStageId stageId) {
        return new StageRunView(UUID.randomUUID().toString(), stageId, flowRunId, StageRunStatus.REQUESTED, null, Instant.now(), null);
    }

    public static StageRunView acknowledged(StageRunView previousView, String executorId) {
        return new StageRunView(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.ACKNOWLEDGED.resolveComparedTo(previousView.getStageRunStatus()), executorId,
                previousView.getStartTime(),
                previousView.getEndTime());
    }

    public static StageRunView launched(StageRunView previousView, Instant startTime) {
        return new StageRunView(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.RUNNING.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                startTime,
                previousView.getEndTime());
    }

    public static StageRunView failed(StageRunView previousView, Instant endTime) {
        return new StageRunView(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.FAILED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }

    public static StageRunView cancelled(StageRunView previousView, Instant endTime) {
        return new StageRunView(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.CANCELLED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }

    public static StageRunView success(StageRunView previousView, Instant endTime) {
        return new StageRunView(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.SUCCESS.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }
}
