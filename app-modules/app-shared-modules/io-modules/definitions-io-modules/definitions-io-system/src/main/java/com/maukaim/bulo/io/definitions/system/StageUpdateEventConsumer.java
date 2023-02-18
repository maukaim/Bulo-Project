package com.maukaim.bulo.io.definitions.system;

import com.maukaim.bulo.io.definitions.system.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdate(StageUpdateEvent event);
}
