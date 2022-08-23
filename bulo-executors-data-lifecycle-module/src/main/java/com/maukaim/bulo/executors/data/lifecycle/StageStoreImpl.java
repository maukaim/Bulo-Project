package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.stages.Stage;

import java.util.HashMap;
import java.util.Map;

public class StageStoreImpl implements StageStore {
    private final Map<String, Stage> cache;

    public StageStoreImpl(Map<String, Stage> initialCache){
        this.cache = new HashMap<>(initialCache);
    }

    @Override
    public Stage getById(String globalStageId) {
        return this.cache.get(globalStageId);
    }
}
