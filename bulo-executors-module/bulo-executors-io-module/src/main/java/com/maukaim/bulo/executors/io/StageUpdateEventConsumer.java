package com.maukaim.bulo.executors.io;

import com.maukaim.bulo.executors.io.in.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdateEvent(StageUpdateEvent stageUpdateEvent);
}
