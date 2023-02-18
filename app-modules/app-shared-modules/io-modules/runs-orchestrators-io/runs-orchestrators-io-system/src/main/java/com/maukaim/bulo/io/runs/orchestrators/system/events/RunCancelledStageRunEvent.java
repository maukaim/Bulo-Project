package com.maukaim.bulo.io.runs.orchestrators.system.events;

import java.time.Instant;

public class RunCancelledStageRunEvent extends BasicStageRunEvent {

    public RunCancelledStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant, StageRunEventType.RUN_CANCELLED);
    }
}
