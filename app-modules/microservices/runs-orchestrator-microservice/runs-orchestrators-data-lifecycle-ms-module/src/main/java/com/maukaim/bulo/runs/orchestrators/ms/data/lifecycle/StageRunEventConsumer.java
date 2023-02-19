package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.io.executors.system.StageRunEvent;

public interface StageRunEventConsumer {
    void onStageRunEvent(StageRunEvent stageRunEvent);
}
