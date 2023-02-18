package com.maukaim.bulo.io.executors.out;

import com.maukaim.bulo.io.executors.out.model.StageRunEventType;

import java.time.Instant;

public class StartRunStageRunEvent extends StageRunEvent {

    public StartRunStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    public StageRunEventType getEventType() {
        return StageRunEventType.LAUNCH_RUN;
    }
}
