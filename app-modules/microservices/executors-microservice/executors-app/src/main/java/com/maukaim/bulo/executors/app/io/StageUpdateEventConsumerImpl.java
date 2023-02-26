package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.executors.data.stages.Stage;
import com.maukaim.bulo.executors.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.io.stages.client.model.StageDto;
import com.maukaim.bulo.io.stages.client.model.TechnicalStageDto;
import com.maukaim.bulo.io.stages.system.StageUpdateEvent;

public class StageUpdateEventConsumerImpl implements StageUpdateEventConsumer {
    private final StageStore stageStore;
    private final StageAdapter stageAdapter;

    public StageUpdateEventConsumerImpl(StageStore stageStore, StageAdapter stageAdapter) {
        this.stageStore = stageStore;
        this.stageAdapter = stageAdapter;
    }

    @Override
    public void onStageUpdateEvent(StageUpdateEvent event) {
        System.out.println("Consume event: " + event);
        if (event.getStage().getStageType() == StageType.TECHNICAL) {
            switch (event.getEventType()) {
                case UPDATE -> {
                    Stage stage = resolve(event.getStage());
                    this.stageStore.put(event.getStageId(), stage);
                }
                case DELETE -> this.stageStore.remove(event.getStageId());
            }
        } else {
            System.out.println("Just ignored! We received a NON TECHNICAL stage here.");
        }
    }

    private Stage resolve(StageDto dto) {
        return switch (dto.getStageType()) {
            case TECHNICAL -> this.stageAdapter.adapte((TechnicalStageDto)dto);
            case FUNCTIONAL -> throw new RuntimeException("Impossible, we should not receive Functional Stages here!");
        };
    }
}
