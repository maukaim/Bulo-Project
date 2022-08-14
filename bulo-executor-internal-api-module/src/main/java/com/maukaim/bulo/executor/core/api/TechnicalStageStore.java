package com.maukaim.bulo.executor.core.api;

import com.maukaim.bulo.executor.core.api.stages.TechnicalStage;

public interface TechnicalStageStore {
    TechnicalStage getById(String globalStageId);
}
