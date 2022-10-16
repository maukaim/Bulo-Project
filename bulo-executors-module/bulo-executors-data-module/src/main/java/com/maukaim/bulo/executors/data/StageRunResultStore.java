package com.maukaim.bulo.executors.data;

import com.maukaim.bulo.executors.data.result.StageRunResult;

public interface StageRunResultStore {
    StageRunResult put(StageRunResult result);

    StageRunResult getById(String stageRunId);
}
