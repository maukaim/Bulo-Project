package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;

import java.util.Map;
import java.util.Set;

public interface StageRunService {
    Map<String, StageRun> startRuns(String flowRunId, Map<FlowStageId, Set<StageRunDependency>> flowStageToRunByDependencies);
    StageRun getById(String stageRunId);
    void requestCancel(String stageRunId);
    void requestCancel(String stageRunId, String executorId);
}
