package com.maukaim.bulo.runs.orchestrators.io;

import com.maukaim.bulo.io.executors.shared.IStageRunEvent;

public interface StageRunEventConsumer {
    void onStageRunEvent(IStageRunEvent stageRunEvent);
}
