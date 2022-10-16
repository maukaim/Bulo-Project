package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public class RunCancelledStageRunEvent extends BasicStageRunEvent {

    public RunCancelledStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant, StageRunEventType.RUN_CANCELLED);
    }
}
