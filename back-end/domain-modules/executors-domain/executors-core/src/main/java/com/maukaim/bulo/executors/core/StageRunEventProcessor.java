package com.maukaim.bulo.executors.core;

import com.maukaim.bulo.executors.data.runs.StageRunDependency;

import java.util.Set;

public interface StageRunEventProcessor {
    void onRunRequest(String stageId,
                      String stageRunId,
                      Set<StageRunDependency> dependencies);

    void onCancelRequest(String stageRunId);
}
