package com.maukaim.bulo.flows.ms.data.lifecycle;

import com.maukaim.bulo.flows.data.StageStore;
import com.maukaim.bulo.flows.data.models.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StageStoreImpl implements StageStore {
    private final Map<String, Stage> stageCache;

    public StageStoreImpl(Map<String, Stage> initialCache) {
        this.stageCache = new HashMap<>(initialCache);
    }

    @Override
    public Stage getById(String stageId) {
        return this.stageCache.get(stageId);
    }

    @Override
    public Stage put(Stage stage) {
        return this.stageCache.put(stage.getStageId(), stage);
    }

    @Override
    public Stage remove(String stageId) {
        return this.stageCache.remove(stageId);
    }
}
