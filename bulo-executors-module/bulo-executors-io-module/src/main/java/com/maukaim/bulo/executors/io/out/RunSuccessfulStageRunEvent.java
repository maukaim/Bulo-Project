package com.maukaim.bulo.executors.io.out;

import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public class RunSuccessfulStageRunEvent extends StageRunEvent {

    public RunSuccessfulStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_SUCCESSFUL;
    }
}

