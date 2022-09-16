package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.stages.io.events.StageUpdateEvent;
import com.maukaim.bulo.stages.io.StageUpdateEventConsumer;
import com.maukaim.bulo.stages.io.models.stages.FunctionalStageDto;
import com.maukaim.bulo.stages.io.models.stages.TechnicalStageDto;
import com.maukaim.bulo.stages.persistence.adapters.StageAdapter;
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
            case TECHNICAL -> this.stageAdapter.adapte(event.getStageId(), (TechnicalStageDto) event.getStage());
            case FUNCTIONAL -> this.stageAdapter.adapte(event.getStageId(), (FunctionalStageDto) event.getStage());
        };
        return this.stageStore.save(stage);
    }
}
