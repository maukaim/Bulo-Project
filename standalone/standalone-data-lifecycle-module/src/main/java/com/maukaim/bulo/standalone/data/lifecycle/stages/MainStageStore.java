package com.maukaim.bulo.standalone.data.lifecycle.stages;

import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.stage.Stage;

import java.util.List;
import java.util.Map;

public class MainStageStore implements StageStore {
    private final Map<String, Stage> stagesById;

    public MainStageStore(Map<String, Stage> initialCache) {
        this.stagesById = initialCache;
    }

    @Override
    public Stage getById(String stageId) {
        return this.stagesById.get(stageId);
    }

    @Override
    public Stage put(Stage stage) {
        System.out.println("Will save stage -> " + stage);
        return this.stagesById.put(stage.getStageId(), stage);
    }

    @Override
    public Stage remove(Stage stage) {
        return this.stagesById.remove(stage.getStageId());
    }

    @Override
    public List<Stage> getAll() {
        return this.stagesById.values().stream().toList();
    }
}
