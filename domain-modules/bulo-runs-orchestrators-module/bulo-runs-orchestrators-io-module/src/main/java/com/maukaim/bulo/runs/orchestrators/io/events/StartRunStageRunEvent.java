package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public class StartRunStageRunEvent extends BasicStageRunEvent {

    public StartRunStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant, StageRunEventType.LAUNCH_RUN);
    }
}
