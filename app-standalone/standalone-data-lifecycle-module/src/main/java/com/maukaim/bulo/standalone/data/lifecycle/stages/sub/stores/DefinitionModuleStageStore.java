package com.maukaim.bulo.standalone.data.lifecycle.stages.sub.stores;

import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.stage.Stage;
import com.maukaim.bulo.standalone.data.lifecycle.UnsupportedDataMethodException;
import com.maukaim.bulo.standalone.data.lifecycle.stages.MainStageStore;
import com.maukaim.bulo.standalone.data.lifecycle.stages.adapters.StageAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class DefinitionModuleStageStore implements StageStore {
    private final MainStageStore mainStageStore;
    private final StageAdapter stageAdapter;

    public DefinitionModuleStageStore(MainStageStore mainStageStore, StageAdapter stageAdapter) {
        this.mainStageStore = mainStageStore;
        this.stageAdapter = stageAdapter;
    }

    @Override
    public boolean contains(String stageId) {
        return false;
    }

    @Override
    public Stage getById(String stageId) {
        com.maukaim.bulo.stages.models.stage.Stage stage = this.mainStageStore.getById(stageId);
        return this.stageAdapter.adapteDefinitionModule(stage);
    }

    @Override
    public List<Stage> getAll() {
        List<com.maukaim.bulo.stages.models.stage.Stage> stages = this.mainStageStore.getAll();
        return stages == null ? List.of() : stages.stream()
                .map(this.stageAdapter::adapteDefinitionModule)
                .collect(Collectors.toList());
    }

    @Override
    public void put(Stage stage) {
        throw UnsupportedDataMethodException.isSubStore();
    }

    @Override
    public void remove(String stageId) {
        throw UnsupportedDataMethodException.isSubStore();
    }
}
