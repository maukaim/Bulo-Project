package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;

import java.util.Set;

public interface StageRunConnector {
    boolean sendCancel(String stageRunId, String executorId);
    boolean sendRun(String stageId, String stageRunId, Set<StageRunDependency> stageRunDependencies);
}
