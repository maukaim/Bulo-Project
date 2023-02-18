package com.maukaim.bulo.io.runs.orchestrators.system;

import com.maukaim.bulo.io.runs.orchestrators.system.events.BasicStageRunEvent;

public interface StageRunEventConsumer {
    void onStageRunEvent(BasicStageRunEvent stageRunEvent);
}
