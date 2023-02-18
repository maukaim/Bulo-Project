package com.maukaim.bulo.io.flows;

import com.maukaim.bulo.io.flows.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdate(StageUpdateEvent event);
}
