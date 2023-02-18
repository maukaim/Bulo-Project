package com.maukaim.bulo.io.executors.out;

import com.maukaim.bulo.io.executors.out.model.StageRunEventType;

import java.time.Instant;

public class RunSuccessfulStageRunEvent extends StageRunEvent {

    public RunSuccessfulStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_SUCCESSFUL;
    }
}

