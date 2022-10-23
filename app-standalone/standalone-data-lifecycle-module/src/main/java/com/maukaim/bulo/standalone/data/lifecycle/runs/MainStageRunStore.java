package com.maukaim.bulo.standalone.data.lifecycle.runs;

import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.Context;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.HashMap;
import java.util.Map;

public class MainStageRunStore implements StageRunStore {
    private final Map<String, StageRun> mappedStageRunId;

    public MainStageRunStore(Map<String, StageRun> initialCache) {
        this.mappedStageRunId = new HashMap<>(initialCache);
    }

    @Override
    public void put(String stageRunId, StageRun technicalStageRun) {
        this.mappedStageRunId.put(stageRunId, technicalStageRun);
    }

    @Override
    public StageRun getById(String stageRunId) {
        return this.mappedStageRunId.get(stageRunId);
    }

    @Override
    public Context<?> getContext(String stageRunId) {
        StageRun technicalStageRun = this.mappedStageRunId.get(stageRunId);
        return technicalStageRun == null ? null : technicalStageRun.getContext();
    }
}
