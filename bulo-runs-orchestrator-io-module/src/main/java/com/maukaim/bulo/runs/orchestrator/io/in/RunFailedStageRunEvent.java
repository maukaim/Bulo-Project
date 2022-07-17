package com.maukaim.bulo.runs.orchestrator.io.in;

import com.maukaim.bulo.common.io.StageRunEventType;

import java.time.Instant;

public class RunFailedStageRunEvent extends BasicStageRunEvent {

    public RunFailedStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_FAILED;
    }
}
