package com.maukaim.bulo.io.runs.orchestrators.events;

import com.maukaim.bulo.io.runs.orchestrators.events.StageRunEventType;

import java.time.Instant;

public class RunCancelledStageRunEvent extends BasicStageRunEvent {

    public RunCancelledStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant, StageRunEventType.RUN_CANCELLED);
    }
}
