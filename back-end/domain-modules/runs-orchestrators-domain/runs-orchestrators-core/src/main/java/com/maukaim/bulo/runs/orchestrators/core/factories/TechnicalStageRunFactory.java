package com.maukaim.bulo.runs.orchestrators.core.factories;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.commons.utils.TimeHelper;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.TechnicalStageRunStatus;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class TechnicalStageRunFactory {

    public TechnicalStageRun toBeRequested(RunContext<?> runContext, ContextStageId stageId, Set<RunDependency> stageRunDependencies) {
        return new TechnicalStageRun(UUID.randomUUID().toString(), stageId, runContext, TechnicalStageRunStatus.TO_BE_REQUESTED, null, stageRunDependencies, null, null);
    }

    public TechnicalStageRun requested(TechnicalStageRun previousView) {
        return new TechnicalStageRun(previousView.getStageRunId(), previousView.getContextualizedStageId(), previousView.getContext(), TechnicalStageRunStatus.REQUESTED, null, previousView.getStageRunDependencies(), Instant.now(), previousView.getEndTime());
    }

    public TechnicalStageRun acknowledged(TechnicalStageRun previousView, String executorId) {
        return new TechnicalStageRun(previousView.getStageRunId(),
                previousView.getContextualizedStageId(),
                previousView.getContext(),
                TechnicalStageRunStatus.ACKNOWLEDGED.resolveComparedTo(previousView.getStatus()),
                executorId,
                previousView.getStageRunDependencies(),
                previousView.getStartTime(),
                previousView.getEndTime());
    }

    public TechnicalStageRun launched(TechnicalStageRun previousView, Instant startTime) {
        return new TechnicalStageRun(previousView.getStageRunId(), previousView.getContextualizedStageId(), previousView.getContext(), TechnicalStageRunStatus.RUNNING.resolveComparedTo(previousView.getStatus()), previousView.getExecutorId(),
                previousView.getStageRunDependencies(), startTime,
                previousView.getEndTime());
    }

    public TechnicalStageRun toBeCancelled(TechnicalStageRun previousView) {
        return new TechnicalStageRun(previousView.getStageRunId(), previousView.getContextualizedStageId(), previousView.getContext(), TechnicalStageRunStatus.TO_BE_CANCELLED.resolveComparedTo(previousView.getStatus()), previousView.getExecutorId(),
                previousView.getStageRunDependencies(), previousView.getStartTime(), previousView.getEndTime());
    }

    public TechnicalStageRun cancelled(TechnicalStageRun previousView, Instant endTime) {
        return new TechnicalStageRun(previousView.getStageRunId(), previousView.getContextualizedStageId(), previousView.getContext(), TechnicalStageRunStatus.CANCELLED.resolveComparedTo(previousView.getStatus()), previousView.getExecutorId(),
                previousView.getStageRunDependencies(), previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }

    public TechnicalStageRun failed(TechnicalStageRun previousView, Instant endTime) {
        return new TechnicalStageRun(previousView.getStageRunId(), previousView.getContextualizedStageId(), previousView.getContext(), TechnicalStageRunStatus.FAILED.resolveComparedTo(previousView.getStatus()), previousView.getExecutorId(),
                previousView.getStageRunDependencies(), previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }

    public TechnicalStageRun success(TechnicalStageRun previousView, Instant endTime) {
        return new TechnicalStageRun(previousView.getStageRunId(), previousView.getContextualizedStageId(), previousView.getContext(), TechnicalStageRunStatus.SUCCESS.resolveComparedTo(previousView.getStatus()), previousView.getExecutorId(),
                previousView.getStageRunDependencies(), previousView.getStartTime(),
                TimeHelper.isAfter(endTime, previousView.getEndTime()) ? endTime : previousView.getEndTime());
    }
}
