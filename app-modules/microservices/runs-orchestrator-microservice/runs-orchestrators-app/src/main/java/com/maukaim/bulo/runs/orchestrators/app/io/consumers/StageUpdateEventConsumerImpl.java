package com.maukaim.bulo.runs.orchestrators.app.io.consumers;

import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;
import com.maukaim.bulo.io.runs.orchestrators.system.StageUpdateEventConsumer;
import com.maukaim.bulo.io.runs.orchestrators.system.events.StageUpdateEvent;

public class StageUpdateEventConsumerImpl implements StageUpdateEventConsumer {
    private final FunctionalStageStore functionalStageStore;

    public StageUpdateEventConsumerImpl(FunctionalStageStore functionalStageStore) {
        this.functionalStageStore = functionalStageStore;
    }

    @Override
    public void onStageUpdate(StageUpdateEvent event) {
        System.out.println("Consumes Stage update event...: " + event);
        switch (event.getStage().getStageType()) {
            case TECHNICAL -> System.out.println("Technical Stage ignored.");
            case FUNCTIONAL -> consumeFunctionalStageEvent(event);
        }
    }

    private void consumeFunctionalStageEvent(StageUpdateEvent event) {
        switch (event.getEventType()) {
            case UPDATE -> functionalStageStore.put(event.getStageId(), event.getStage().getDefinitionId());
            case DELETE -> functionalStageStore.remove(event.getStageId());
        }
    }
}
