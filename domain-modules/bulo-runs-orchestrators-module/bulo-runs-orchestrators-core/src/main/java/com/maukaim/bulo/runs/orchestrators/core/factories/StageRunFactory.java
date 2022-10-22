package com.maukaim.bulo.runs.orchestrators.core.factories;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.core.utils.TimeHelper;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunStatus;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class StageRunFactory {

    public static StageRun toBeRequested(String flowRunId, ContextualizedStageId stageId, Set<StageRunDependency> stageRunDependencies) {
        return new StageRun(UUID.randomUUID().toString(), stageId, flowRunId, StageRunStatus.TO_BE_REQUESTED, null, stageRunDependencies, null, null);
    }

    public static StageRun requested(StageRun previousView) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.REQUESTED, null, previousView.getStageRunDependencies(), Instant.now(), previousView.getEndTime());
    }


    public static StageRun acknowledged(StageRun previousView, String executorId) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.ACKNOWLEDGED.resolveComparedTo(previousView.getStageRunStatus()), executorId,
                previousView.getStageRunDependencies(), previousView.getStartTime(),
                previousView.getEndTime());
    }

    public static StageRun launched(StageRun previousView, Instant startTime) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.RUNNING.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStageRunDependencies(), startTime,
                previousView.getEndTime());
    }

    public static StageRun toBeCancelled(StageRun previousView) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.TO_BE_CANCELLED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStageRunDependencies(), previousView.getStartTime(), previousView.getEndTime());
    }

    public static StageRun cancelled(StageRun previousView, Instant endTime) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.CANCELLED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStageRunDependencies(), previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }

    public static StageRun failed(StageRun previousView, Instant endTime) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.FAILED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStageRunDependencies(), previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }

    public static StageRun success(StageRun previousView, Instant endTime) {
        return new StageRun(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.SUCCESS.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId(),
                previousView.getStageRunDependencies(), previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }
}
