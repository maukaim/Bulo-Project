package com.maukaim.bulo.definitions.ms.data.lifecycle;

import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StageStoreImpl implements StageStore {
    private final Map<String, Stage> stageCache;

    public StageStoreImpl(Map<String, Stage> stageCache) {
        this.stageCache = new HashMap<>(stageCache);
    }

    @Override
    public boolean contains(String stageId) {
        return this.stageCache.containsKey(stageId);
    }

    @Override
    public Stage getById(String stageId) {
        return this.stageCache.get(stageId);
    }

    @Override
    public List<Stage> getAll() {
        return this.stageCache.values().stream().toList();
    }

    @Override
    public void put(Stage stage) {
        System.out.println("Storing stage: "+ stage);
        this.stageCache.put(stage.getStageId(), stage);
    }

    @Override
    public void remove(String stageId) {
        this.stageCache.remove(stageId);
    }
}
