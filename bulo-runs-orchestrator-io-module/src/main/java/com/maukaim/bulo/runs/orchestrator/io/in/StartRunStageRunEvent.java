package com.maukaim.bulo.runs.orchestrator.io.in;

import java.time.Instant;

public class StartRunStageRunEvent extends BasicStageRunEvent {

    public StartRunStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant);
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.LAUNCH_RUN;
    }
}
