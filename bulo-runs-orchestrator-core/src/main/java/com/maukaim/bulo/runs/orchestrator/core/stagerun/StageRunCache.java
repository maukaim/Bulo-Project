package com.maukaim.bulo.runs.orchestrator.core.stagerun;

import com.maukaim.bulo.commons.models.FlowStageId;

public interface StageRunCache {
    void put(String stageRunId, FlowStageId stageId, String flowRunId);
    String getFlowRunId(String stageRunId);
    FlowStageId getStageId(String stageRunId);
}
