package com.maukaim.boule.flows.orchestrator.stage.run;

import com.maukaim.boule.flows.orchestrator.flow.view.FlowStageId;

public interface StageRunCache {
    void put(String stageRunId, FlowStageId stageId, String flowRunId);
    String getFlowRunId(String stageRunId);
    FlowStageId getStageId(String stageRunId);
}
