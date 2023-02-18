package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.executors.system.dtos.StageRunEventType;

import java.time.Instant;

public class StartRunStageRunEvent extends StageRunEvent {

    public StartRunStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    public StageRunEventType getEventType() {
        return StageRunEventType.LAUNCH_RUN;
    }
}
