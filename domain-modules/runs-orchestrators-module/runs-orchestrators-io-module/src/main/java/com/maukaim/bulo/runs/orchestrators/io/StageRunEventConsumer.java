package com.maukaim.bulo.runs.orchestrators.io;

import com.maukaim.bulo.commons.io.IStageRunEvent;

public interface StageRunEventConsumer {
    void onStageRunEvent(IStageRunEvent stageRunEvent);
}
