package com.maukaim.bulo.executors.io.out;

import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public class RunCancelledStageRunEvent extends StageRunEvent {

    public RunCancelledStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_CANCELLED;
    }
}