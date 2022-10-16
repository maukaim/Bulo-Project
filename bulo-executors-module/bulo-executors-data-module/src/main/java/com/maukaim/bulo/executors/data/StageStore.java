package com.maukaim.bulo.executors.data;

import com.maukaim.bulo.executors.data.stages.Stage;

public interface StageStore {
    Stage getById(String stageId);
    void put(String stageId, Stage stage);
    Stage remove(String stageId);
}
