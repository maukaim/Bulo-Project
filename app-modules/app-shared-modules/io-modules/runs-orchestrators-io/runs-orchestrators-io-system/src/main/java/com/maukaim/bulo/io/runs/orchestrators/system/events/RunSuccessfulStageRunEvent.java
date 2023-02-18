package com.maukaim.bulo.io.runs.orchestrators.system.events;

import java.time.Instant;

public class RunSuccessfulStageRunEvent extends BasicStageRunEvent {

    public RunSuccessfulStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant, StageRunEventType.RUN_SUCCESSFUL);
    }
}

