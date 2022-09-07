package com.maukaim.bulo.executors.data;

import com.maukaim.bulo.executors.data.stages.Stage;

public interface StageStore {
    Stage getById(String globalStageId);
    void put(String globalStageId, Stage stage);
    Stage remove(String globalStageId);
}
