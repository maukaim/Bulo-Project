package com.maukaim.bulo.definitions.registry.app.io;

import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.stage.Stage;
import com.maukaim.bulo.definitions.ms.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.io.stages.client.model.StageDto;
import com.maukaim.bulo.io.stages.system.events.StageUpdateEvent;

public class StageUpdateEventConsumerImpl implements StageUpdateEventConsumer {
    private final StageStore stageStore;
    private final StageAdapter stageAdapter;

    public StageUpdateEventConsumerImpl(StageStore stageStore, StageAdapter stageAdapter) {
        this.stageStore = stageStore;
        this.stageAdapter = stageAdapter;
    }

    @Override
    public void onStageUpdate(StageUpdateEvent event) {
        System.out.println("Consume event: " + event);
        switch (event.getEventType()) {
            case UPDATE -> save(event.getStage());
            case DELETE -> delete(event.getStageId());
        }
    }

    private void save(StageDto dto) {
        Stage stageToSave = this.stageAdapter.adapte(dto);
        this.stageStore.put(stageToSave);
    }

    private void delete(String stageId) {
        this.stageStore.remove(stageId);
    }
}
