package com.maukaim.bulo.runs.orchestrators.data;

import com.maukaim.bulo.commons.models.FlowStageId;

public interface StageRunStore {
    void put(String stageRunId, FlowStageId stageId, String flowRunId);
    String getFlowRunId(String stageRunId);
}
