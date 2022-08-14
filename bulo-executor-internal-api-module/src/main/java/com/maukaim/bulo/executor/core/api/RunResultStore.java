package com.maukaim.bulo.executor.core.api;

import com.maukaim.bulo.executor.core.api.stages.TechnicalStage;

public interface RunResultStore {
    TechnicalStage getById(String globalStageId);
}
