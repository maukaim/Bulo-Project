package com.maukaim.bulo.definitions.data;

import com.maukaim.bulo.definitions.data.stage.Stage;

import java.util.List;

public interface StageStore {
    boolean contains(String stageId);
    Stage getById(String stageId);
    List<Stage> getAll();
    void put(Stage stage);
    void remove(String stageId);
}
