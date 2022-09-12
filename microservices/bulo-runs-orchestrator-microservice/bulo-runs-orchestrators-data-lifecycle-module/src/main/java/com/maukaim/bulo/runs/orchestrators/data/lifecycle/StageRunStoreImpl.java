package com.maukaim.bulo.runs.orchestrators.data.lifecycle;

import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.HashMap;
import java.util.Map;

public class StageRunStoreImpl implements StageRunStore {
    private Map<String, StageRun> mappedStageRunId = new HashMap<>();

    @Override
    public void put(String stageRunId, StageRun stageRun) {
        this.mappedStageRunId.put(stageRunId, stageRun); //TODO: Doit etre fait de maniere a pouvoir les partager entre instances de orchestrator !
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
