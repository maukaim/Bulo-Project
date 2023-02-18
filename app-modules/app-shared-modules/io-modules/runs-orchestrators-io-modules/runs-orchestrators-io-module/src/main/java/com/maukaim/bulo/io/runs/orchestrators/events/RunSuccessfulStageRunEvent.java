package com.maukaim.bulo.io.runs.orchestrators.events;

import com.maukaim.bulo.io.executors.shared.StageRunEventType;

import java.time.Instant;

public class RunSuccessfulStageRunEvent extends BasicStageRunEvent {

    public RunSuccessfulStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant, StageRunEventType.RUN_SUCCESSFUL);
    }
}

