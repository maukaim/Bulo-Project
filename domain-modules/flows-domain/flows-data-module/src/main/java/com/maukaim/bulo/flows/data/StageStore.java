package com.maukaim.bulo.flows.data;

import com.maukaim.bulo.flows.data.models.stage.Stage;

public interface StageStore {
    Stage getById(String stageId);
    Stage put(Stage stage);
    Stage remove(String stageId);
}
