package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;

import java.util.Set;

public interface StageRunConnector {
    boolean requestCancel(String stageRunId, String executorId);

    boolean requestRun(String stageId, String stageRunId, Set<RunDependency> stageRunDependencies);

    void propagateFunctionalStageRunAcknowleged(String stageRunId);

    void propagateFunctionalStageRunStarted(String stageRunId);

    void propagateFunctionalStageRunCancelled(String stageRunId);

    void propagateFunctionalStageRunFailed(String stageRunId);

    void propagateFunctionalStageRunSuccessful(String stageRunId);
}
