package com.maukaim.bulo.executor.core.api;

import com.maukaim.bulo.executor.core.api.stages.Stage;

public interface StageStore {
    Stage getById(String globalStageId);
}
