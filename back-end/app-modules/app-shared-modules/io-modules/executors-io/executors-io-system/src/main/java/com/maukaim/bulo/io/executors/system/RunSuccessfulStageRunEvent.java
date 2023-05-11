package com.maukaim.bulo.io.executors.system;

import java.time.Instant;

import static com.maukaim.bulo.io.executors.system.dtos.StageRunEventType.RUN_SUCCESSFUL;

public class RunSuccessfulStageRunEvent extends StageRunEvent {

    public RunSuccessfulStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, RUN_SUCCESSFUL, instant);
    }
}

