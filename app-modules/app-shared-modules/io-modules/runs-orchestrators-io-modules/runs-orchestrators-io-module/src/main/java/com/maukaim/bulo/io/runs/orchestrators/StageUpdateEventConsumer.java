package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.runs.orchestrators.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdate(StageUpdateEvent event);
}
