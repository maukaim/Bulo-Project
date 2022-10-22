package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface StageRunService {
    Map<String,StageRun> getNextStageRun(String flowRunId, Map<ContextualizedStageId, Set<StageRunDependency>> flowStageToRunByDependencies);
    Map<String, StageRun> startRuns(Collection<StageRun> stageRunToBeRequested);
    StageRun getById(String stageRunId);
    void requestCancel(String stageRunId);
    void requestCancel(String stageRunId, String executorId);
}
