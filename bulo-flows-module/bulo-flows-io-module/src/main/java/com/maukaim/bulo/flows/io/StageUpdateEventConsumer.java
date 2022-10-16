package com.maukaim.bulo.flows.io;

import com.maukaim.bulo.flows.io.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdate(StageUpdateEvent event);
}
