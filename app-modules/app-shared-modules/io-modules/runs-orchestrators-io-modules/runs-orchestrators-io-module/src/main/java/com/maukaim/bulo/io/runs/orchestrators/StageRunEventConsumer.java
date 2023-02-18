package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.runs.orchestrators.events.BasicStageRunEvent;

public interface StageRunEventConsumer {
    void onStageRunEvent(BasicStageRunEvent stageRunEvent);
}
