package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public class RunFailedStageRunEvent extends BasicStageRunEvent {

    public RunFailedStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant, StageRunEventType.RUN_FAILED);
    }
}
