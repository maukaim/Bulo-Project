package com.maukaim.bulo.standalone.data.lifecycle.runs;

import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.HashMap;
import java.util.Map;

public class MainStageRunStore implements StageRunStore {
    private final Map<String, StageRun> mappedStageRunId;

    public MainStageRunStore(Map<String, StageRun> initialCache) {
        this.mappedStageRunId = new HashMap<>(initialCache);
    }

    @Override
    public void put(String stageRunId, StageRun stageRun) {
        this.mappedStageRunId.put(stageRunId, stageRun);
    }

    @Override
    public StageRun getById(String stageRunId) {
        return this.mappedStageRunId.get(stageRunId);
    }

    @Override
    public String getFlowRunId(String stageRunId) {
        StageRun stageRun = this.mappedStageRunId.get(stageRunId);
        return stageRun == null ? null : stageRun.getFlowRunId();
    }
}
