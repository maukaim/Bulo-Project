package com.maukaim.bulo.runs.orchestrator.io.in;

import com.maukaim.bulo.commons.models.StageRunEventType;

import java.time.Instant;

public class RunSuccessfulStageRunEvent extends BasicStageRunEvent {

    public RunSuccessfulStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_SUCCESSFUL;
    }
}

