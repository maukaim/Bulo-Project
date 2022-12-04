package com.maukaim.bulo.standalone.data.lifecycle.stages.sub.stores;

import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.stages.Stage;
import com.maukaim.bulo.standalone.data.lifecycle.UnsupportedDataMethodException;
import com.maukaim.bulo.standalone.data.lifecycle.stages.MainStageStore;
import com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.StageAdapter;

public class ExecutorModuleStageStore implements StageStore {
    private final MainStageStore mainStageStore;
    private final StageAdapter stageAdapter;

    public ExecutorModuleStageStore(MainStageStore mainStageStore, StageAdapter stageAdapter) {
        this.mainStageStore = mainStageStore;
        this.stageAdapter = stageAdapter;
    }

    @Override
    public Stage getById(String stageId) {
        com.maukaim.bulo.stages.models.stage.Stage stage = this.mainStageStore.getById(stageId);
        return this.stageAdapter.adapteExecutorModule(stage);
    }

    @Override
    public void put(String stageId, Stage stage) {
        throw UnsupportedDataMethodException.isSubStore();
    }

    @Override
    public Stage remove(String stageId) {
        throw UnsupportedDataMethodException.isSubStore();
    }
}
