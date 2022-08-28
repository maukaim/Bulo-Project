package com.maukaim.bulo.flows.core.impl;

import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.data.StageStore;
import com.maukaim.bulo.flows.data.models.stage.Stage;

public class StageServiceImpl implements StageService {
    private final StageStore stageStore;

    public StageServiceImpl(StageStore stageStore) {
        this.stageStore = stageStore;
    }

    @Override
    public Stage getById(String globalStageId) {
        return this.stageStore.getById(globalStageId);
    }
}
