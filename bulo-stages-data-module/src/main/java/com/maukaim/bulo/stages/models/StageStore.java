package com.maukaim.bulo.stages.models;

import com.maukaim.bulo.stages.models.stage.Stage;

import java.util.List;

public interface StageStore {
    Stage getById(String globalStageId);

    Stage put(Stage stage);

    Stage remove(Stage stage);

    List<Stage> getAll();
}
