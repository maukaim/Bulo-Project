package com.maukaim.bulo.definitions.io;

import com.maukaim.bulo.definitions.io.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdate(StageUpdateEvent event);
}
