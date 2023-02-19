package com.maukaim.bulo.io.executors.system;

import java.time.Instant;

import static com.maukaim.bulo.io.executors.system.dtos.StageRunEventType.RUN_FAILED;

public class RunFailedStageRunEvent extends StageRunEvent {

    public RunFailedStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, RUN_FAILED, instant);
    }

}
