package com.maukaim.bulo.io.executors.system;

import java.time.Instant;

import static com.maukaim.bulo.io.executors.system.dtos.StageRunEventType.LAUNCH_RUN;

public class StartRunStageRunEvent extends StageRunEvent {

    public StartRunStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, LAUNCH_RUN, instant);
    }

}
