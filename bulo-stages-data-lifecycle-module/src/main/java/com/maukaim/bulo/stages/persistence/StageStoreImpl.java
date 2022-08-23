package com.maukaim.bulo.stages.persistence;

import com.maukaim.bulo.commons.io.StageUpdateEventType;
import com.maukaim.bulo.io.StageUpdateEvent;
import com.maukaim.bulo.io.StageUpdateEventPublisher;
import com.maukaim.bulo.io.stages.StageData;
import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.persistence.adapters.StageDataAdapter;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class StageStoreImpl implements StageStore {
    private final Map<String, Stage> stagesById;
    private final StageUpdateEventPublisher eventPublisher;
    private final StageDataAdapter stageDataAdapter;

    public StageStoreImpl(Map<String, Stage> initialCache, StageUpdateEventPublisher eventPublisher, StageDataAdapter stageDataAdapter) {
        this.stagesById = initialCache;
        this.eventPublisher = eventPublisher;
        this.stageDataAdapter = stageDataAdapter;
    }

    @Override
    public Stage getById(String globalStageId) {
        return this.stagesById.get(globalStageId);
    }

    @Override
    public Stage put(Stage stage) {
        StageData stageData = this.stageDataAdapter.adapte(stage);
        boolean published = this.eventPublisher.publish(new StageUpdateEvent(stage.getStageId(), stageData, StageUpdateEventType.UPDATE, Instant.now()));
        return published ? stage : this.save(stage);
    }

    @Override
    public Stage remove(Stage stage) {
        boolean published = this.eventPublisher.publish(new StageUpdateEvent(stage.getStageId(), null, StageUpdateEventType.DELETE, Instant.now()));
        return published ? stage : this.delete(stage.getStageId());
    }

    @Override
    public List<Stage> getAll() {
        return this.stagesById.values().stream().toList();
    }

    public Stage save(Stage stage) {
        System.out.println("Will save stage -> " + stage);
        return this.stagesById.put(stage.getStageId(), stage);
    }

    public Stage delete(String stageId) {
        return this.stagesById.remove(stageId);
    }
}
