package com.maukaim.bulo.runs.orchestrator.stage.run;

import com.maukaim.bulo.flows.api.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunView;

import java.util.Map;
import java.util.Set;

public interface StageRunService {
    Map<String, StageRunView> startRuns(String flowRunId, Set<FlowStageId> stageIds);
    void requestCancel(String stageRunId);
    void requestCancel(String stageRunId, String executorId);
}
