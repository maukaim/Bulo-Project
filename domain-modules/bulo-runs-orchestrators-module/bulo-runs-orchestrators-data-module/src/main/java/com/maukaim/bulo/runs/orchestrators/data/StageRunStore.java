package com.maukaim.bulo.runs.orchestrators.data;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.Context;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

public interface StageRunStore {
    void put(String stageRunId, StageRun technicalStageRun);

    StageRun getById(String stageRunId);

    Context getContext(String stageRunId);
}
