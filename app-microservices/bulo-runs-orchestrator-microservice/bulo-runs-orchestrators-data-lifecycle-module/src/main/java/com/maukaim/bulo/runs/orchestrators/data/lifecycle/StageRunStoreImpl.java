package com.maukaim.bulo.runs.orchestrators.data.lifecycle;

import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.Context;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.HashMap;
import java.util.Map;

public class StageRunStoreImpl implements StageRunStore {
    private Map<String, StageRun> mappedStageRunId = new HashMap<>();

    @Override
    public void put(String stageRunId, StageRun technicalStageRun) {
        this.mappedStageRunId.put(stageRunId, technicalStageRun); //TODO: Doit etre fait de maniere a pouvoir les partager entre instances de orchestrator !
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
