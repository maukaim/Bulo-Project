package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.io.stages.system.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdate(StageUpdateEvent event);
}
