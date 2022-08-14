package com.maukaim.bulo.executor.core.api;

import com.maukaim.bulo.executor.core.api.run.result.TechnicalStageRunResult;

public interface TechnicalStageRunResultStore {
    TechnicalStageRunResult put(TechnicalStageRunResult result);

    TechnicalStageRunResult getById(String stageRunId);
}
