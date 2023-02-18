package com.maukaim.bulo.io.runs.orchestrators.events;

import com.maukaim.bulo.io.executors.shared.StageRunEventType;

import java.time.Instant;

public class RunFailedStageRunEvent extends BasicStageRunEvent {

    public RunFailedStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant, StageRunEventType.RUN_FAILED);
    }
}
