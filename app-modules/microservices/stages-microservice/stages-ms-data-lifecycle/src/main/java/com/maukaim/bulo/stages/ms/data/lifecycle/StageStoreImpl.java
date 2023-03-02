package com.maukaim.bulo.stages.ms.data.lifecycle;

import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.io.stages.client.model.StageDto;
import com.maukaim.bulo.io.stages.system.StageUpdateEventType;
import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.data.lifecycle.stages.client.StageDtoAdapter;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class StageStoreImpl implements StageStore {
    private final Map<String, Stage> stagesById;
    private final StageUpdateEventPublisher eventPublisher;
    private final StageDtoAdapter stageDtoAdapter;

    public StageStoreImpl(Map<String, Stage> initialCache, StageUpdateEventPublisher eventPublisher, StageDtoAdapter stageDtoAdapter) {
        this.stagesById = initialCache;
        this.eventPublisher = eventPublisher;
        this.stageDtoAdapter = stageDtoAdapter;
    }

    @Override
    public Stage getById(String stageId) {
        return this.stagesById.get(stageId);
    }

    @Override
    public Stage put(Stage stage) {
        StageDto stageDto = this.stageDtoAdapter.adapte(stage);
        boolean published = this.eventPublisher.publish(new StageUpdateEvent(stage.getStageId(), stageDto, StageUpdateEventType.UPDATE, Instant.now()));
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
