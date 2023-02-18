package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.io.stages.system.events.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdateEvent(StageUpdateEvent stageUpdateEvent);
}
