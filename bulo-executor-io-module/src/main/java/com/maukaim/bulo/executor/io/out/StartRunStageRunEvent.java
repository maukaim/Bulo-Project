package com.maukaim.bulo.executor.io.out;

import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public class StartRunStageRunEvent extends StageRunEvent {

    public StartRunStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.LAUNCH_RUN;
    }
}
