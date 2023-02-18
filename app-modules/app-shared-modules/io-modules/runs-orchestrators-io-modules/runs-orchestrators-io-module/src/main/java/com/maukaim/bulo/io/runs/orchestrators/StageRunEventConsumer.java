package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.executors.shared.IStageRunEvent;

public interface StageRunEventConsumer {
    void onStageRunEvent(IStageRunEvent stageRunEvent);
}
