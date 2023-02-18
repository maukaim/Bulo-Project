package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.executors.system.out.StageRunResultEvent;

public interface StageRunResultEventConsumer {
    void onStageRunResultEvent(StageRunResultEvent event);
}
