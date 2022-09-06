package com.maukaim.bulo.executors.core;

import com.maukaim.bulo.executors.data.runs.StageRunDependency;

import java.util.Set;

public interface StageRunEventProcessor {
    void onStageRunRequest(String globalStageId,
                           String stageRunId,
                           Set<StageRunDependency> dependencies);
}
