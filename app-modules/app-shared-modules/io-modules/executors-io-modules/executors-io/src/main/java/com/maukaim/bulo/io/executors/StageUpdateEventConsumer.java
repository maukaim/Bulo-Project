package com.maukaim.bulo.io.executors;

import com.maukaim.bulo.io.executors.in.StageUpdateEvent;

public interface StageUpdateEventConsumer {
    void onStageUpdateEvent(StageUpdateEvent stageUpdateEvent);
}
