package com.maukaim.bulo.io.executors.system.out;

import com.maukaim.bulo.io.executors.system.out.model.StageRunEventType;

import java.time.Instant;

public class RunCancelledStageRunEvent extends StageRunEvent {

    public RunCancelledStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_CANCELLED;
    }
}
