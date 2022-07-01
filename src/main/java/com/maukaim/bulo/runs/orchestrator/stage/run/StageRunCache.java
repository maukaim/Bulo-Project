package com.maukaim.bulo.runs.orchestrator.stage.run;

import com.maukaim.bulo.runs.orchestrator.flow.view.FlowStageId;

public interface StageRunCache {
    void put(String stageRunId, FlowStageId stageId, String flowRunId);
    String getFlowRunId(String stageRunId);
    FlowStageId getStageId(String stageRunId);
}
