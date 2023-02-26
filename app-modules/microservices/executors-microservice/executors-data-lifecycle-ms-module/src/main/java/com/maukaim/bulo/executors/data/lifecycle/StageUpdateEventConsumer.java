package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.io.stages.system.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdateEvent(StageUpdateEvent stageUpdateEvent);
}
