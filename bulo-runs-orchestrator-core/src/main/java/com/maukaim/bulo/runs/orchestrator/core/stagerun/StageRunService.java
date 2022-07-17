package com.maukaim.bulo.runs.orchestrator.core.stagerun;

import com.maukaim.bulo.flows.api.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRun;

import java.util.Map;
import java.util.Set;

public interface StageRunService {
    Map<String, StageRun> startRuns(String flowRunId, Set<FlowStageId> stageIds);
    void requestCancel(String stageRunId);
    void requestCancel(String stageRunId, String executorId);
}
