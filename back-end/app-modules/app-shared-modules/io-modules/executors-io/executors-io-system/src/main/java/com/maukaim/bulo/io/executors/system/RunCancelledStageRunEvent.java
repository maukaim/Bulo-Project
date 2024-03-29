package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.executors.system.dtos.StageRunEventType;

import java.time.Instant;

public class RunCancelledStageRunEvent extends StageRunEvent {

    public RunCancelledStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, StageRunEventType.RUN_CANCELLED, instant);
    }

}
