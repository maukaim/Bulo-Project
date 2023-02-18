package com.maukaim.bulo.io.flows.system;

import com.maukaim.bulo.io.flows.system.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdate(StageUpdateEvent event);
}
