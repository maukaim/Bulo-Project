package com.maukaim.bulo.executor.core.api;

import com.maukaim.bulo.executor.core.api.result.StageRunResult;

public interface StageRunResultStore {
    StageRunResult put(StageRunResult result);

    StageRunResult getById(String stageRunId);
}
