package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public class RunSuccessfulStageRunEvent extends BasicStageRunEvent {

    public RunSuccessfulStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant, StageRunEventType.RUN_SUCCESSFUL);
    }
}

