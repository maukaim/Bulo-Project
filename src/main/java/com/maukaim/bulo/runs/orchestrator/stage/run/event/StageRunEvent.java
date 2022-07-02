package com.maukaim.bulo.runs.orchestrator.stage.run.event;

import com.maukaim.bulo.common.models.events.ExternalEvent;

public interface StageRunEvent extends ExternalEvent {
    StageRunEventType getEventType();
    String getStageRunId();
}
