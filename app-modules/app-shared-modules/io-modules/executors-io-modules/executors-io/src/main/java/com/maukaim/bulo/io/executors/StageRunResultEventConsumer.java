package com.maukaim.bulo.io.executors;

import com.maukaim.bulo.io.executors.out.StageRunResultEvent;

public interface StageRunResultEventConsumer {
    void onStageRunResultEvent(StageRunResultEvent event);
}