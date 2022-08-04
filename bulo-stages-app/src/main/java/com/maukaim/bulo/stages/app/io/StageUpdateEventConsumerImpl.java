package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.io.StageUpdateEvent;
import com.maukaim.bulo.io.StageUpdateEventConsumer;
import com.maukaim.bulo.io.stages.FunctionalStageData;
import com.maukaim.bulo.io.stages.TechnicalStageData;
import com.maukaim.bulo.stages.core.StageAdapter;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.persistence.StageStoreImpl;

public class StageUpdateEventConsumerImpl implements StageUpdateEventConsumer {
    private final StageStoreImpl stageStore;
    private final StageAdapter stageAdapter;

    public StageUpdateEventConsumerImpl(StageStoreImpl stageStore, StageAdapter stageAdapter) {
        this.stageStore = stageStore;
        this.stageAdapter = stageAdapter;
    }

    @Override
    public void consume(StageUpdateEvent event) {
        System.out.println("Consuming event: " + event);

        switch (event.getEventType()) {
            case UPDATE -> this.update(event);
            case DELETE -> this.stageStore.delete(event.getStageId());
        }
    }

    private Stage update(StageUpdateEvent event) {
        Stage stage = switch (event.getStage().getStageType()) {
            case TECHNICAL -> this.stageAdapter.adapte(event.getStageId(), (TechnicalStageData) event.getStage());
            case FUNCTIONAL -> this.stageAdapter.adapte(event.getStageId(), (FunctionalStageData) event.getStage());
        };
        return this.stageStore.save(stage);
    }
}
