package com.maukaim.bulo.executors.io.out;

import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public class RunFailedStageRunEvent extends StageRunEvent {

    public RunFailedStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_FAILED;
    }
}
