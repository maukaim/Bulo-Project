package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.io.executors.system.StageRunResultEvent;

public interface StageRunResultEventConsumer {
    void onStageRunResultEvent(StageRunResultEvent event);
}
