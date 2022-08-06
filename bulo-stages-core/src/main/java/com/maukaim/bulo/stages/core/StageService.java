package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.io.stages.StageData;
import com.maukaim.bulo.stages.core.stage.StageCreateReport;
import com.maukaim.bulo.stages.models.stage.Stage;

import java.util.List;

public interface StageService {
    List<Stage> getAll();

    Stage getById(String id);

    StageCreateReport addStage(StageData stageData);

    Stage remove(String stageId);
}
