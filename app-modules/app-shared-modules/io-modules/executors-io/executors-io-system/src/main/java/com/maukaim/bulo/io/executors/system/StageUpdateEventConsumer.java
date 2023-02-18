package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.executors.system.in.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdateEvent(StageUpdateEvent stageUpdateEvent);
}
