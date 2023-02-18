package com.maukaim.bulo.io.definitions;

import com.maukaim.bulo.io.definitions.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdate(StageUpdateEvent event);
}
