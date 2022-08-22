package com.maukaim.bulo.executor.data.management;

import com.maukaim.bulo.executor.core.api.StageStore;
import com.maukaim.bulo.executor.core.api.stages.Stage;

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
