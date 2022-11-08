package com.maukaim.bulo.runs.orchestrators.data;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

public interface StageRunStore extends ContextStore<FunctionalStageRun, String> {
    void put(String stageRunId, StageRun<?> technicalStageRun);

    StageRun<?> getById(String stageRunId);

    RunContext getContext(String stageRunId);
}
