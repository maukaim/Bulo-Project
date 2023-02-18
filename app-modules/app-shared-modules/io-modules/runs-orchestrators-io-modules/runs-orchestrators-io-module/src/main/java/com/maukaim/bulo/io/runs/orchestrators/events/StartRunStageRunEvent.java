package com.maukaim.bulo.io.runs.orchestrators.events;

import com.maukaim.bulo.io.executors.shared.StageRunEventType;

import java.time.Instant;

public class StartRunStageRunEvent extends BasicStageRunEvent {

    public StartRunStageRunEvent(String stageRunId, Instant instant) {
        super(stageRunId, instant, StageRunEventType.LAUNCH_RUN);
    }
}