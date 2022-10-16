package com.maukaim.bulo.runs.orchestrators.data;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

public interface StageRunStore {
    void put(String stageRunId, StageRun stageRun);
    StageRun getById(String stageRunId);
    String getFlowRunId(String stageRunId);
}
