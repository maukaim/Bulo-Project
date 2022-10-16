package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.stages.core.validators.StageCreateReport;
import com.maukaim.bulo.stages.models.stage.Stage;

import java.util.List;

public interface StageService {
    List<Stage> getAll();

    Stage getById(String id);

    StageCreateReport addStage(Stage stage);

    Stage remove(String stageId);
}
