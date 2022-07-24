package com.maukaim.bulo.runs.orchestrator.io.in;

import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public class RunCancelledStageRunEvent extends BasicStageRunEvent {

    public RunCancelledStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_CANCELLED;
    }
}
