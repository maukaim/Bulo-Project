package com.maukaim.boule.flows.orchestrator.stage.run.event;

import com.maukaim.boule.flows.orchestrator.stage.run.event.StageRunEventType;

public interface StageRunEvent {
    StageRunEventType getEventType();
    String getStageRunId();
}
